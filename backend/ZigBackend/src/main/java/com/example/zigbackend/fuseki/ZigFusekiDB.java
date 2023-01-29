package com.example.zigbackend.fuseki;

import com.example.zigbackend.dto.MetadataSearchParams;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;

import java.io.IOException;
import java.util.List;

public class ZigFusekiDB {

    public static void save(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws IOException {
        Model model = ZigMetadataExtractor.extract(zahtevZaPriznanjeZiga);
        FusekiWriter.saveRdfGraphToDatabase(model);
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
