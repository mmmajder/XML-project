package com.example.autorskapravabackend.db;

import com.example.autorskapravabackend.marshal.Marshal;
import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import com.example.autorskapravabackend.utils.AuthenticationUtilities;
import org.xml.sax.SAXException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResenjeZahtevaDB {

    private static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

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

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

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

    public static ResenjeZahteva write(ResenjeZahteva resenjeZahteva) throws ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException, IOException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        String collectionId = "/db/XWS-PROJECT/resenja";

        // initialize database driver
        System.out.println("[INFO] Loading driver class: " + conn.driver);
        Class<?> cl = Class.forName(conn.driver);

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res;
        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId, conn);
            String documentName = "resenjeAutorskoPravo_" + resenjeZahteva.getBrojPrijave().replace('/', '_');
            res = (XMLResource) col.createResource(documentName, XMLResource.RESOURCE_TYPE);
            res.setContent(Marshal.marshal(resenjeZahteva));
            System.out.println("[INFO] Storing the document: " + res.getId());
            col.storeResource(res);
            System.out.println("[INFO] Done.");
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return resenjeZahteva;
    }

    public static ResenjeZahteva dobaviPoBrojuPrijave(String brojPrijave) throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        String collectionId = "/db/XWS-PROJECT/resenja";
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        ResenjeZahteva resenjeZahteva = null;
        try {
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            String xPathExp = "//resenje_zahteva[brojPrijave='" + brojPrijave + "']";
            ResourceSet result = xPathQueryService.query(xPathExp);
            XMLResource res = (XMLResource) result.getIterator().nextResource();
            JAXBContext context = JAXBContext.newInstance(ResenjeZahteva.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            resenjeZahteva = (ResenjeZahteva) unmarshaller.unmarshal(res.getContentAsDOM());
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return resenjeZahteva;
    }

    public static List<ResenjeZahteva> dobaviSvaResenja() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, XMLDBException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        String collectionId = "/db/XWS-PROJECT/resenja";
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        ResenjeZahteva resenjeZahteva;
        List<ResenjeZahteva> resenjaZahteva = null;
        try {
            // get the collection
            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            String xPathExp = "//resenje_zahteva";
            ResourceSet result = xPathQueryService.query(xPathExp);
            ResourceIterator i = result.getIterator();
            XMLResource res;
            resenjaZahteva = new ArrayList<>();
            while (i.hasMoreResources()) {
                res = (XMLResource) i.nextResource();
                JAXBContext context = JAXBContext.newInstance(ResenjeZahteva.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                resenjeZahteva = (ResenjeZahteva) unmarshaller.unmarshal(res.getContentAsDOM());
                resenjaZahteva.add(resenjeZahteva);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
        return resenjaZahteva;
    }
}
