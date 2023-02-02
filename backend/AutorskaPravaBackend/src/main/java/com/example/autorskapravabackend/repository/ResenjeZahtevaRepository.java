package com.example.autorskapravabackend.repository;

import com.example.autorskapravabackend.db.ResenjeZahtevaDB;
import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class ResenjeZahtevaRepository {

    public void kreiraj(ResenjeZahteva resenjeZahteva) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResenjeZahtevaDB.write(resenjeZahteva);
    }

    public ResenjeZahteva dobaviPoBrojuPrijave(String brojPrijave) throws XMLDBException, IOException, JAXBException {
        return ResenjeZahtevaDB.dobaviPoBrojuPrijave(brojPrijave);
    }
}
