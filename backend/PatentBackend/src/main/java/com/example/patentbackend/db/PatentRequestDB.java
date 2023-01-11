package com.example.patentbackend.db;

import com.example.patentbackend.marshal.Marshal;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.utils.ExistAuthenticationUtilities;
import com.example.patentbackend.utils.DBSetup;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStream;
import java.util.List;

import static com.example.patentbackend.utils.Utils.formatNameOfRequestForPatent;

public class PatentRequestDB {

    private static final String TARGET_NAMESPACE = "com.example.patentbackend.model";

    public static int getNumberOfRequests() {
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            return col.getResourceCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void save(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        OutputStream marshaledPatent = Marshal.marshalPatent(zahtevZaPriznanjePatenta);
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            String documentId = formatNameOfRequestForPatent(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), ".xml");
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaledPatent);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.getResource(formatNameOfRequestForPatent(brojPrijave, ".xml"));
            if (res != null) {
                System.out.println(res.getContent());
                JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(res.getContentAsDOM());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ZahtevZaPriznanjePatenta> getAllByFilter(String filter) {
        try {
            ExistAuthenticationUtilities.ConnectionProperties conn = ExistAuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);

            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            // make the service aware of namespaces, using the default one
            xpathService.setNamespace("pat", "http://www.ftn.uns.ac.rs/patent");
            String xpathExp = "//pat:Stanje[text()='" + filter + "']/ancestor::pat:Zahtev_za_priznanje_patenta";

            // execute xpath expression
            System.out.println("[INFO] Invoking XPath query service for: " + xpathExp);
            ResourceSet result = xpathService.query(xpathExp);

            // handle the results
            System.out.println("[INFO] Handling the results... ");

            ResourceIterator i = result.getIterator();
            Resource res = null;
            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    System.out.println(res.getContent());

                } finally {

                    // don't forget to cleanup resources
                    try {
                        ((EXistResource) res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, ExistAuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, ExistAuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {

        org.xmldb.api.base.Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                org.xmldb.api.base.Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    org.xmldb.api.base.Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, conn);
        } else {
            return col;
        }
    }


}
