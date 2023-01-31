package com.example.autorskapravabackend.rdf;

import com.example.autorskapravabackend.dto.MetadataSearchParams;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import org.apache.jena.rdf.model.Model;

import java.io.IOException;
import java.util.List;

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
