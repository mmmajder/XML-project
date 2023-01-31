package com.example.patentbackend.rdf;

import java.io.IOException;
import java.util.List;

import com.example.patentbackend.dto.MetadataSearchParams;
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

    public static List<String> findByMetadata(MetadataSearchParams param) throws IOException {
        return FusekiReader.findByMetadata(param);
    }

    public static List<String> findByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        return FusekiReader.findByMetadata(params);
    }

    public static String getRdfString(String brojPrijave) throws Exception {
        return FusekiReader.getRdfString(brojPrijave);
    }

    public static String getJsonString(String brojPrijave) throws Exception {
        return FusekiReader.getJsonString(brojPrijave);
    }
}
