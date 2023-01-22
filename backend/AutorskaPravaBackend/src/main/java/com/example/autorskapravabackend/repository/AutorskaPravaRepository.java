package com.example.autorskapravabackend.repository;

import com.example.autorskapravabackend.db.AutorskaPravaRequestDB;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.rdf.AutorskaPravaFusekiDB;
import com.example.autorskapravabackend.rdf.FusekiReader;

import java.util.List;

public class AutorskaPravaRepository {

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return AutorskaPravaRequestDB.getZahtev(brojPrijave);
    }

    public List<ZahtevZaAutorskaPrava> getZahtevi() {
        return AutorskaPravaRequestDB.getZahtevi();
    }

    public int getNumberOfRequests() {
        return AutorskaPravaRequestDB.getNumberOfRequests();
    }

    public void createRequest(ZahtevZaAutorskaPrava zahtev) {
        AutorskaPravaRequestDB.save(zahtev);
        AutorskaPravaFusekiDB.save(zahtev);
    }

    public String generateRDF(String brojPrijave) throws Exception {
        return FusekiReader.getRdfString(brojPrijave);
    }

    public String generateJSON(String brojPrijave) throws Exception {
        return FusekiReader.getJsonString(brojPrijave);
    }
}
