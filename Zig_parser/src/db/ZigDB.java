package db;

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

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import zig.ZahtevZaPriznanjeZiga;

public class ZigDB {
	private static final String TARGET_NAMESPACE = "src.zig";
	
	public static void save(ZahtevZaPriznanjeZiga zahtev) throws JAXBException, XMLDBException {
		OutputStream marshaledZahtev = marshal(zahtev);
		
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
//                System.out.println(res.getContent());
                //JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
                return unmarshal(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<XMLResource> searchResources(String text, boolean matchCase) throws Exception {
    	Collection col = getCollection();
        List<XMLResource> resources = new ArrayList<XMLResource>();
    	XMLResource resource = null;
    	
        try {
        	XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            String xPathExp = createXPathExpressionForTextSearch(text, matchCase);
            ResourceIterator iter = xPathQueryService.query(xPathExp).getIterator();
            
            while (iter.hasMoreResources()) {
            	resource = (XMLResource) iter.nextResource();
            	resources.add(resource);
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
	
    private static String createXPathExpressionForTextSearch(String text, boolean matchCase) {
//    	/*[contains(lower-case(.), '%s')]
    	String xpath = "/*[contains(";
    	
    	if (!matchCase) {
    		xpath = xpath.concat("lower-case(.)");
    		text = text.toLowerCase();
    	} else {
    		xpath = xpath.concat(".");
    	}
    	
    	xpath = xpath.concat(", ").concat("\"").concat(text).concat("\"");
    	xpath = xpath.concat(")]");

    	return xpath;
    }
    
    private static Collection getCollection() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		AuthenticationUtilitiesDB.ConnectionProperties conn = AuthenticationUtilitiesDB.loadProperties();
        String collectionId = DBSetup.setupDBConnection(conn);
        Collection col = getOrCreateCollection(collectionId, conn);
        col.setProperty(OutputKeys.INDENT, "yes");
        
        return col;
	}
	
	
	private static OutputStream marshal(ZahtevZaPriznanjeZiga zahtev) throws JAXBException, XMLDBException {
		OutputStream marshaledZahtev = new ByteArrayOutputStream();
		JAXBContext context = JAXBContext.newInstance("zig");
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(zahtev, marshaledZahtev);
        
		return marshaledZahtev;
	}
	
	private static ZahtevZaPriznanjeZiga unmarshal(XMLResource res) throws JAXBException, XMLDBException {
		JAXBContext context = JAXBContext.newInstance("zig");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(res.getContentAsDOM());
        
		return zahtevZaPriznanjeZiga;
	}
    
	public static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, AuthenticationUtilitiesDB.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static org.xmldb.api.base.Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilitiesDB.ConnectionProperties conn) throws XMLDBException {

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
    
    
    
    
    
    
    
    
    // search with multiple words: vvv
    public static List<XMLResource> searchResources(List<String> words, boolean matchCase) throws Exception {
    	Collection col = getCollection();
        List<XMLResource> resources = new ArrayList<XMLResource>();
    	XMLResource resource = null;
    	
        try {
        	XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            String xPathExp = createXPathExpressionForTextSearch(words, matchCase);
            ResourceIterator iter = xPathQueryService.query(xPathExp).getIterator();
            
            while (iter.hasMoreResources()) {
            	resource = (XMLResource) iter.nextResource();
            	resources.add(resource);
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
    
    private static String createXPathExpressionForTextSearch(List<String> words, boolean matchCase) {
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
    // search with multiple words ^^^
}
