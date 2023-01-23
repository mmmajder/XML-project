package com.example.zigbackend.ktvalidators;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.example.zigbackend.db.ZigDB;
import com.example.zigbackend.fuseki.FusekiReader;
import com.example.zigbackend.fuseki.FusekiWriter;
import com.example.zigbackend.fuseki.ZigMetadataExtractor;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.transformer.ZigTransformer;
import com.example.zigbackend.utils.SparqlUtil;
import org.apache.jena.rdf.model.Model;
import org.xmldb.api.base.XMLDBException;

public class ZigFusekiTest {
	public static void main(String[] args) throws IOException, JAXBException, XMLDBException {
        test();
    }

    public static void test() throws IOException, JAXBException, XMLDBException {
    	ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = KTZig.createZahtevZaPriznanjeZiga();
    	
//    	// pdf and html
//    	ZigTransformer.generateHTMLZig(zahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan HTML");
//    	ZigTransformer.generatePDFZig(zahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan PDF"); 
  	
    	// cuvanje u bazu
    	ZigDB.save(zahtevZaPriznanjeZiga);
    	
    	// fuseki
    	Model metadataModel = ZigMetadataExtractor.extract(zahtevZaPriznanjeZiga);
    	
    	printModelAsRdfXml(metadataModel);
    	
    	FusekiWriter.saveRdfGraphToDatabase(metadataModel);
    	FusekiReader.readAllDataFromDatabase();
    	
    	// ucitavanje iz baze
    	ZahtevZaPriznanjeZiga ucitanZahtevZaPriznanjeZiga = ZigDB.getZahtev(zahtevZaPriznanjeZiga.getBrojPrijaveZiga());

    	System.out.println("\nIz baze je ucitan zahtev sa brojem prijave: ");
    	System.out.println(ucitanZahtevZaPriznanjeZiga.getBrojPrijaveZiga());
    	
//    	 pdf and html
    	ZigTransformer.generateHTMLZig(ucitanZahtevZaPriznanjeZiga);
    	System.out.println("izgenerisan HTML");
    	ZigTransformer.generatePDFZig(ucitanZahtevZaPriznanjeZiga);
    	System.out.println("izgenerisan PDF");
    }
    
    private static void printModelAsRdfXml(Model model) {
    	System.out.println("[INFO] Rendering model as RDF/XML...");
    	model.write(System.out, SparqlUtil.RDF_XML);
    }
}
