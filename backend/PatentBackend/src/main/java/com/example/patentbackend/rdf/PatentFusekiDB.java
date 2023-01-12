package com.example.patentbackend.rdf;

import java.io.IOException;

import com.example.patentbackend.model.*;
import org.apache.jena.rdf.model.Model;

public class PatentFusekiDB {
    public static void save(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        System.out.println("[INFO] Marshalling customized address book:");
        Model metadataModel = PatentMetadataExtractor.extract(zahtevZaPriznanjePatenta);
        try {
            FusekiWriter.saveRdfGraphToDatabase(metadataModel);
            FusekiReader.readAllDataFromDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
