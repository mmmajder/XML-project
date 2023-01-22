package com.example.zigbackend.fuseki;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.example.zigbackend.utils.AuthenticationUtilitiesFuseki;
import com.example.zigbackend.utils.SparqlUtil;
import org.apache.commons.text.StringSubstitutor;
import org.apache.jena.query.QueryException;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;

public class FusekiReader {
//	private static final String QUERY_FILE = "data/sparql/query.rq";
	private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/ZigData/data/metadata
	
	public static void readAllDataFromDatabase() throws IOException {
		AuthenticationUtilitiesFuseki.ConnectionProperties conn = AuthenticationUtilitiesFuseki.loadProperties();
		
		System.out.println("\n[INFO] Retrieving ALL triples from RDF Databse from the graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");
		
		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		ResultSetFormatter.out(System.out, results);
		query.close() ;
	}
}
