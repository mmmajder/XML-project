package com.example.patentbackend.service;

import com.example.patentbackend.dto.NazivPrijaveDTO;
import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.mapper.Mapper;
import com.example.patentbackend.model.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.repository.PatentRepository;
import com.example.patentbackend.transformer.PatentTransformer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class PatentService {

    private PatentRepository patentRepository = new PatentRepository();

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return patentRepository.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public ZahtevZaPriznanjePatenta createZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatentaDTO zahtevZaPriznanjePatentaDTO) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = Mapper.mapToZahtevZaPriznanjePatenta(zahtevZaPriznanjePatentaDTO);
        setBrojPrijave(zahtevZaPriznanjePatenta);
        patentRepository.createPatentRequest(zahtevZaPriznanjePatenta);
        return zahtevZaPriznanjePatenta;
    }

    private void setBrojPrijave(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacije = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
        String brojPrijave = "P-" + LocalDate.now().getYear() + "/" + (patentRepository.getNumberOfRequests() + 1);
        osnovneInformacije.setBrojPrijave(brojPrijave);
        osnovneInformacije.setStanje("NA_CEKANJU");
        zahtevZaPriznanjePatenta.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(osnovneInformacije);
    }

    public List<ZahtevZaPriznanjePatenta> getAllPending() {
        return patentRepository.getAllPending();
    }

    public List<ZahtevZaPriznanjePatenta> getAllAccepted() {
        return patentRepository.getAllAccepted();
    }

    public boolean generateHTML(NazivPrijaveDTO brojPrijave) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getZahtevZaPriznanjePatenta(brojPrijave.getNaziv());
        if (zahtevZaPriznanjePatenta==null) {
            return false;
        }
        PatentTransformer.generateHTMLPatent(zahtevZaPriznanjePatenta);
        return true;
    }
    public boolean generatePDF(NazivPrijaveDTO brojPrijave) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getZahtevZaPriznanjePatenta(brojPrijave.getNaziv());
        if (zahtevZaPriznanjePatenta==null) {
            return false;
        }
        PatentTransformer.generatePDFPatent(zahtevZaPriznanjePatenta);
        return true;
    }




//    public List<ZahtevZaPriznanjePatenta> getAllZahtevZaPriznanjePatenta() {
//        return patentRepository.getAll();
//    }
}
