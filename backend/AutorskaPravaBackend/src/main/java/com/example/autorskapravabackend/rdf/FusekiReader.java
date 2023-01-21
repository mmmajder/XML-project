package com.example.autorskapravabackend.rdf;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiReader {
    private static final String GRAPH_URI = "metadata"; // http://localhost:8080/fuseki/AutorskaPravaData/data/metadata

    public static void readAllDataFromDatabase() throws IOException {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();

        System.out.println("\n[INFO] Retrieving ALL triples from RDF Database from the graph \"" + conn.dataEndpoint + "/" + GRAPH_URI + "\".");
        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + "/" + GRAPH_URI, "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();
    }

    public static String getRdfString(String brojPrijave) throws Exception {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        String sparqlQuery = SparqlUtil.selectDataByBrojPrijave(conn.dataEndpoint + "/" + GRAPH_URI, brojPrijave);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        return ResultSetFormatter.asXMLString(results);
    }

    public static String getJsonString(String brojPrijave) throws Exception {
        FusekiAuthenticationUtilities.ConnectionProperties conn = FusekiAuthenticationUtilities.loadProperties();
        String sparqlQuery = SparqlUtil.selectDataByBrojPrijave(conn.dataEndpoint + "/" + GRAPH_URI, brojPrijave);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        return outputStream.toString();
    }

}
