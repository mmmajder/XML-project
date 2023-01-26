package com.example.zigbackend.fuseki;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.example.zigbackend.utils.AuthenticationUtilitiesFuseki;
import com.example.zigbackend.utils.SparqlUtil;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class FusekiWriter {
	private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/ZigData/data/metadata
	
	public static void saveRdfGraphToDatabase(Model metadataModel) throws IOException {
		AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();
		
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
}
