package fuseki;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
		AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		
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
