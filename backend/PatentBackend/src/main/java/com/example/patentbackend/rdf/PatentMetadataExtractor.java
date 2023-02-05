package com.example.patentbackend.rdf;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.utils.Utils;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class PatentMetadataExtractor {
    final static private String PATENT_NAMESPACE = "http://www.ftn.uns.ac.rs/patent";

    static public Model extract(ZahtevZaPriznanjePatenta zahtev) {
        //podnosilac_email
        // pronalazac_email
        // punomocnik_email
        // vrsta_prijave
        // broj_prijave (id)
        // datum_podnosenja
        Model model = ModelFactory.createDefaultModel();

        String idPrijave = Utils.formatNameOfRequestForPatent(zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), ".xml");
        Resource resource = model.createResource(PATENT_NAMESPACE + "/" + zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave());

        addRDFTripletToModel(model, resource, "podnosilac_email", zahtev.getSadrzajZahtevaZaPriznanjePatenta().getPodaciOPodnosiocuPrijave().getPodnosilacPrijave().getEPosta());
        addRDFTripletToModel(model, resource, "pronalazac_email", zahtev.getSadrzajZahtevaZaPriznanjePatenta().getPodaciOPronalazacu().getPronalazac().getEPosta());
        addRDFTripletToModel(model, resource, "punomocnik_email", zahtev.getSadrzajZahtevaZaPriznanjePatenta().getPodaciOPunomocniku().getPunomocnik().getEPosta());
        addRDFTripletToModel(model, resource, "vrsta_prijave", zahtev.getSadrzajZahtevaZaPriznanjePatenta().getPodaciOPrijavi().getVrstaPrijave());
        addRDFTripletToModel(model, resource, "broj_prijave", zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave());
        return model;
    }

    static private void addRDFTripletToModel(Model model, Resource resource, String propertyName, String propertyValue) {
        Property prop = model.createProperty(PATENT_NAMESPACE + "/predicate/" + propertyName);
        Literal val = model.createLiteral(propertyValue);
        Statement triplet = model.createStatement(resource, prop, val);

        model.add(triplet);
    }
}
