package com.example.zigbackend.fuseki;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class ZigMetadataExtractor {
	
	final static private String ZIG_NAMESPACE = "http://www.ftn.uns.ac.rs/zig";

	static public Model extract(ZahtevZaPriznanjeZiga zahtev) {
		Model model = ModelFactory.createDefaultModel();
		
		String id = zahtev.getBrojPrijaveZiga();
		Resource resource = model.createResource(ZIG_NAMESPACE + "/" + id);

		addRDFTripletToModel(model, resource, "podnosilac_email", zahtev.getPodnosilacPrijave().getKontakt().getEmail());
		addRDFTripletToModel(model, resource, "punomocnik_email", zahtev.getPunomocnik().getKontakt().getEmail());
		addRDFTripletToModel(model, resource, "predstavnik_email", zahtev.getPredstavnik().getKontakt().getEmail());
		addRDFTripletToModel(model, resource, "tip_ziga", zahtev.getZig().getTipZiga().toString());
		addRDFTripletToModel(model, resource, "broj_prijave", zahtev.getBrojPrijaveZiga());
		addRDFTripletToModel(model, resource, "datum_podnosenja", zahtev.getDatumPodnosenja().toString());
		
		return model;
	}
	
	static private void addRDFTripletToModel(Model model, Resource resource, String propertyName, String propertyValue) {
		Property prop = model.createProperty(ZIG_NAMESPACE + "/predicate/" + propertyName);
		Literal val = model.createLiteral(propertyValue);
		Statement triplet = model.createStatement(resource, prop, val);
		
		model.add(triplet);
	}
}
