package com.example.autorskapravabackend.rdf;

import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import org.apache.jena.rdf.model.Model;

import java.io.IOException;

public class AutorskaPravaFusekiDB {
    public static void save(ZahtevZaAutorskaPrava zahtev) {
        System.out.println("[INFO] Marshalling customized address book:");
        Model metadataModel = AutorskaPravaMetadataExtractor.extract(zahtev);
        try {
            FusekiWriter.saveRdfGraphToDatabase(metadataModel);
            FusekiReader.readAllDataFromDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
