package com.example.patentbackend.rdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class FusekiWriter {
    private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/PatentData/data/metadata

    public static void saveRdfGraphToDatabase(Model metadataModel) throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        // zbog prirode metoda koje slede, potrebno je sadrzaj grafa (tj. modela) pretociti u instancu ByteArrayOutputStream-a
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        metadataModel.write(out, SparqlUtil.NTRIPLES);

        System.out.println("\n[INFO] Populating graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\" with extracted metadata.");
        // ovde se prethodno popunjeni ByteArray objekat prosledjuje da bi se napravio quary string za komunikaciju sa bazom
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + "/" + GRAPH_URI, new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();
    }

    public static void saveRDF() throws IOException {
//		AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
//
//		Model model = ModelFactory.createDefaultModel();
//		model.read(RDF_FILE);
//
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		model.write(out, SparqlUtil.NTRIPLES);
//		System.out.println("[INFO] Rendering model as RDF/XML...");
//		model.write(System.out, SparqlUtil.RDF_XML);
//
//		// Delete all of the triples in all of the named graphs
////		UpdateRequest request = UpdateFactory.create() ;
////        request.add(SparqlUtil.dropAll());
//
//        UpdateRequest request = UpdateFactory.create() ;
//        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
//        processor.execute();
//
//     // Creating the first named graph and updating it with RDF data
//     	System.out.println("[INFO] Writing the triples to a named graph \"" + GRAPH_URI + "\".");
//		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + GRAPH_URI, new String(out.toByteArray()));
//		System.out.println(sparqlUpdate);
//
//		// UpdateRequest represents a unit of execution
//		UpdateRequest update = UpdateFactory.create(sparqlUpdate);
//		processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
//		processor.execute();
//
//		// ovo iznad je za jedan graf, treba ponoviti za vise grafova
    }
}
