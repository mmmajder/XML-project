package com.example.zigbackend.db;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;

import com.example.zigbackend.model.EStatus;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.service.MarshallerZig;
import com.example.zigbackend.utils.AuthenticationUtilitiesDB;
import com.example.zigbackend.utils.DBSetup;
import org.exist.xmldb.EXistResource;
import org.exist.xmldb.RemoteXMLResource;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class ZigDB {
	private static final String TARGET_NAMESPACE = "com.example.zigbackend.model";
	
	public static void save(ZahtevZaPriznanjeZiga zahtev) throws JAXBException, XMLDBException {
        OutputStream marshaledZahtev = MarshallerZig.marshal(zahtev);

        try {
            Collection col = getCollection();
            String documentId = "zig_" + zahtev.getBrojPrijaveZiga().replace('/', '_') + ".xml";
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaledZahtev);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ZahtevZaPriznanjeZiga getZahtev(String brojPrijave) {
        try {
            Collection col = getCollection();
            XMLResource res = (XMLResource) col.getResource("zig_" + brojPrijave.replace('/', '_') + ".xml");
            if (res != null) {
                return MarshallerZig.unmarshal(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNumberOfZahtevi() {
        try {
            AuthenticationUtilitiesDB.ConnectionProperties conn = AuthenticationUtilitiesDB.loadProperties();
            String collectionId = DBSetup.setupDBConnection(conn);
            Collection col = getOrCreateCollection(collectionId, conn);
            return col.getResourceCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<XMLResource> getAllByYear(String yy) {
//        String xpathExp = "//sz:Broj_prijave_ziga[text()='" + "/" + yy + "']/ancestor::sz:Zahtev_za_priznanje_ziga";
        String xpathExp = "//sz:Zahtev_za_priznanje_ziga//sz:Broj_prijave_ziga[contains(.,'/" + yy + "')]";// /ancestor::sz:Zahtev_za_priznanje_ziga";

        return getAllByFilter(xpathExp);
    }

    public static List<XMLResource> getAllByStatus(EStatus status) {
        String xpathExp = "//sz:Status[text()='" + status + "']/ancestor::sz:Zahtev_za_priznanje_ziga";

        return getAllByFilter(xpathExp);
    }

    private static List<XMLResource> getAllByFilter(String xpathExp){
        List<XMLResource> resources = new ArrayList<XMLResource>();

        try {
            Collection col = getCollection();
            XPathQueryService xpathService = getXPathQueryServiceForZig(col);
            ResourceSet result = xpathService.query(xpathExp);
            collectXMLResourcesFromResult(result, resources, col);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resources;
    }

    private static XPathQueryService getXPathQueryServiceForZig(Collection col) throws XMLDBException {
        XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xpathService.setProperty("indent", "yes");
        xpathService.setNamespace("sz", "http://www.ftn.uns.ac.rs/zig");

        return xpathService;
    }

    private static void collectXMLResourcesFromResult(ResourceSet result, List<XMLResource> resources, Collection col) throws XMLDBException {
        ResourceIterator iter = result.getIterator();
        XMLResource resource = null;

        try {
            while (iter.hasMoreResources()) {
                resource = (XMLResource) iter.nextResource();
                resources.add(resource);
            }
        } finally {
            if (resource != null) {
                ((EXistResource) resource).freeResources();
            }
            if (col != null) {
                col.close();
            }
        }
    }

    public static List<XMLResource> searchResourcesForText(List<String> words, boolean matchCase) throws Exception {
        Collection col = getCollection();
        List<XMLResource> resources = new ArrayList<XMLResource>();
        XMLResource resource = null;

        try {
            // ovde ne moze da se koristi getXPathQueryServiceForZig() jer postavi namespace, informacija o namespace-u se prenese na Resource
            // i kasnije ne moze da se izmarshaluje zbog ns ... zato filtraciju obradjeni/neobgradjeni ne raditi preko baze
            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            String xPathExp = createXPathExpressionForTextSearch(words, matchCase);
            ResourceIterator iter = xPathQueryService.query(xPathExp).getIterator();

            while (iter.hasMoreResources()) {
                Resource res =  iter.nextResource();
                System.out.println(res.getContent());
                RemoteXMLResource resxml = (RemoteXMLResource) res;

//                System.out.println(res);
//                Object content  = res.getContent();
//                System.out.println(content);
//
//                System.out.println(resxml);
//                Node n = resxml.getContentAsDOM();
//                System.out.println(n);
//                System.out.println(n.getChildNodes());
//
//                resources.add(resource);
//                System.out.println(res);
//                System.out.println(res.getContent());
//                resource = (XMLResource) iter.nextResource();
//                System.out.println(resource);
//                System.out.println(resource.toString());
//                System.out.println(resource.getContentAsDOM());
                resources.add(resxml);
            }

            return resources;
        } finally {
            if (resource != null) {
                ((EXistResource) resource).freeResources();
            }
            if (col != null) {
                col.close();
            }
        }
    }

    private static String createXPathExpressionForTextSearch(List<String> words, boolean matchCase) { //"//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_UPLATI_TAKSE']//..//sz:Status_priloga='NIJE_PREDATO'"
        int wordsDone = 0;
        String xpath = "/*[";

        for (String word : words){
            xpath = xpath.concat("contains(");

            if (!matchCase) {
                xpath = xpath.concat("lower-case(.)");
                word = word.toLowerCase();
            } else {
                xpath = xpath.concat(".");
            }

            xpath = xpath.concat(", ").concat("\"").concat(word).concat("\"");
            xpath = xpath.concat(")");

            wordsDone++;
            if (wordsDone != words.size()){
                xpath = xpath.concat(" and ");
            }
        }

        xpath = xpath.concat("]");

        return xpath;
    }

    // use this one
    private static Collection getCollection() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        AuthenticationUtilitiesDB.ConnectionProperties conn = AuthenticationUtilitiesDB.loadProperties();
        String collectionId = DBSetup.setupDBConnection(conn);
        Collection col = getOrCreateCollection(collectionId, conn);
        col.setProperty(OutputKeys.INDENT, "yes");

        return col;
    }
	
	private static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilitiesDB.ConnectionProperties conn) throws XMLDBException {
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
