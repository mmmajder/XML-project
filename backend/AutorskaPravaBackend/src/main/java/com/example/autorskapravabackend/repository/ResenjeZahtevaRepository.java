package com.example.autorskapravabackend.repository;

import com.example.autorskapravabackend.db.ResenjeZahtevaDB;
import com.example.autorskapravabackend.model.ResenjeZahteva;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;
import java.util.List;

public class ResenjeZahtevaRepository {
    ResenjeZahtevaDB resenjeZahtevaDatabase = new ResenjeZahtevaDB();

    public ResenjeZahteva kreiraj(ResenjeZahteva resenjeZahteva) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return resenjeZahtevaDatabase.write("resenje-zahteva-autorska", resenjeZahteva.getBrojPrijave() + ".xml", resenjeZahteva);
    }

    public ResenjeZahteva dobaviPoBrojuPrijave(String brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return resenjeZahtevaDatabase.dobaviPoBrojuPrijave(brojPrijave);
    }

    public List<ResenjeZahteva> dobaviSve() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return resenjeZahtevaDatabase.dobaviSve();
    }
}
