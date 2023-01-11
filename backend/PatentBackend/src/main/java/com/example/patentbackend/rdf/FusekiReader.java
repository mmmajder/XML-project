package com.example.patentbackend.rdf;

import java.io.IOException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class FusekiReader {
    //	private static final String QUERY_FILE = "data/sparql/query.rq";
    private static final String GRAPH_URI = "metadata"; // http://localhost:8088/fuseki/PatentData/data/metadata

    public static void readAllDataFromDatabase() throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        System.out.println("\n[INFO] Retrieving ALL triples from RDF Databse from the graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close() ;
    }

//	public static ArrayList<String> run(Map<String, String> params) throws IOException{
//		AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
//
//		String queryTemplate = readFile(QUERY_FILE);
//		String sparqlQuery = StringSubstitutor.replace(queryTemplate, params, "{{", "}}");
//		QueryExecution execution = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
//		ResultSet results = execution.execSelect();
//
//		String varName;
//		RDFNode varValue;
//		ArrayList<String> foundQueries = new ArrayList<>();
//
//
//		while(results.hasNext()) {
//			QuerySolution querySolution = results.next();
//			Iterator<String> variableBindings = querySolution.varNames();
//
//			while(variableBindings.hasNext()) {
//				varName = variableBindings.next();
//				varValue = querySolution.get(varName);
//				System.out.println(varName + " : " + varValue);
//			}
//		}
//
//		ResultSetFormatter.outputAsXML(System.out, results);
//		execution.close();
//
//		return foundQueries;
//	}
//
//	public static String readFile(String path) throws IOException {
//		byte[] encoded = Files.readAllBytes(Paths.get(path));
//
//		// izmeniti UTF 8 ukoliko bude pravilo problema
//		return new String(encoded, StandardCharsets.UTF_8);
//	}
}
