package com.example.autorskapravabackend.db;

import com.example.autorskapravabackend.marshal.Marshal;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.utils.AuthenticationUtilities;
import com.example.autorskapravabackend.utils.DBSetup;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.OutputStream;

import static com.example.autorskapravabackend.utils.Utils.formatNameOfRequest;

public class AutorskaPravaRequestDB {

    private static final String TARGET_NAMESPACE = "com.example.autorskapravabackend.model";

    public static ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        try {
            AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.getResource(formatNameOfRequest(brojPrijave, ".xml"));
            if (res != null) {
                System.out.println(res.getContent());
                JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                return (ZahtevZaAutorskaPrava) unmarshaller.unmarshal(res.getContentAsDOM());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    public static int getNumberOfRequests() {
        try {
            AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            return col.getResourceCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void save(ZahtevZaAutorskaPrava zahtev) {
        OutputStream marshaled = Marshal.marshal(zahtev);
        try {
            AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            String documentId = formatNameOfRequest(zahtev.getInformacijeOZahtevu().getBrojPrijave(), ".xml");
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaled);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {

        org.xmldb.api.base.Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
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
