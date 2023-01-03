package com.example.patentbackend.repository;

import com.example.patentbackend.db.PatentRequestDB;
import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;

import java.util.List;

public class PatentRepository {
//    public List<ZahtevZaPriznanjePatenta> getAll() {
//
//    }

    public void createPatentRequest(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        PatentRequestDB.save(zahtevZaPriznanjePatenta);
    }

    public int getNumberOfRequests() {
        return PatentRequestDB.getNumberOfRequests();
    }

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return PatentRequestDB.getZahtevZaPriznanjePatenta(brojPrijave);
    }
}
