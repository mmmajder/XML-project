package com.example.patentbackend.repository;

import com.example.patentbackend.db.PatentRequestDB;
import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.rdf.PatentFusekiDB;

import java.util.List;

public class PatentRepository {
    public void createPatentRequest(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        PatentRequestDB.save(zahtevZaPriznanjePatenta);
        PatentFusekiDB.save(zahtevZaPriznanjePatenta);
    }

    public int getNumberOfRequests() {
        return PatentRequestDB.getNumberOfRequests();
    }

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return PatentRequestDB.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public List<ZahtevZaPriznanjePatenta> getAllPending() {
        return PatentRequestDB.getAllByFilter("NA_CEKANJU");
    }

    public List<ZahtevZaPriznanjePatenta> getAllAccepted() {
        return PatentRequestDB.getAllByFilter("USPESNO");
    }
}
