package com.example.autorskapravabackend.repository;

import com.example.autorskapravabackend.db.AutorskaPravaRequestDB;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;

public class AutorskaPravaRepository {

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return AutorskaPravaRequestDB.getZahtev(brojPrijave);
    }

    public int getNumberOfRequests() {
        return AutorskaPravaRequestDB.getNumberOfRequests();
    }

    public void createRequest(ZahtevZaAutorskaPrava zahtev) {
        AutorskaPravaRequestDB.save(zahtev);
    }
}
