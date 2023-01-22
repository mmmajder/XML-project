package com.example.zigbackend.db;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.utils.AuthenticationUtilitiesDB;
import com.example.zigbackend.utils.DBSetup;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

public class ZigDB {
	private static final String TARGET_NAMESPACE = "src.zig";
	
	public static void save(ZahtevZaPriznanjeZiga zahtev) throws JAXBException {
		//
		OutputStream marshaledZahtev = new ByteArrayOutputStream();
		JAXBContext context = JAXBContext.newInstance("zig");
		 Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
         marshaller.marshal(zahtev, marshaledZahtev);
        //
         
        try {
            AuthenticationUtilitiesDB.ConnectionProperties conn = AuthenticationUtilitiesDB.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            String documentId = "zig_" + zahtev.getBrojPrijaveZiga().replace('/', '_') + ".xml";
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaledZahtev);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ZahtevZaPriznanjeZiga getZahtev(String brojPrijave) {
        try {
            AuthenticationUtilitiesDB.ConnectionProperties conn = AuthenticationUtilitiesDB.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.getResource("zig_" + brojPrijave.replace('/', '_') + ".xml");
            if (res != null) {
                System.out.println(res.getContent());
                //JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
                
                //
                JAXBContext context = JAXBContext.newInstance("zig");
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(res.getContentAsDOM());
                //
                
                return zahtevZaPriznanjeZiga;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilitiesDB.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilitiesDB.ConnectionProperties conn) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
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

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

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
}
