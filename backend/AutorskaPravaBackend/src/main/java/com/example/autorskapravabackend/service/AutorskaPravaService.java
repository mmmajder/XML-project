package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.InformacijeOZahtevu;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AutorskaPravaService {

    private AutorskaPravaRepository autorskaPravaRepository = new AutorskaPravaRepository();

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return autorskaPravaRepository.getZahtev(brojPrijave);
    }

    public ZahtevZaAutorskaPrava createZahtevZaAutorskaPrava(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = Mapper.dtoToZahtev(dto);
        setBrojPrijave(zahtevZaAutorskaPrava);
        autorskaPravaRepository.createRequest(zahtevZaAutorskaPrava);
        return zahtevZaAutorskaPrava;
    }

    private void setBrojPrijave(ZahtevZaAutorskaPrava zahtev) {
        InformacijeOZahtevu osnovneInformacije = new InformacijeOZahtevu();
        String brojPrijave = "A-" + LocalDate.now().getYear() + "/" + (autorskaPravaRepository.getNumberOfRequests() + 1);
        osnovneInformacije.setBrojPrijave(brojPrijave);
        osnovneInformacije.setDatumPodnosenja(LocalDate.now());
        zahtev.setInformacijeOZahtevu(osnovneInformacije);
    }
//
//    public boolean generateHTML(com.example.patentbackend.dto.NazivPrijaveDTO brojPrijave) {
//        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getZahtevZaPriznanjePatenta(brojPrijave.getNaziv());
//        if (zahtevZaPriznanjePatenta == null) {
//            return false;
//        }
//        com.example.patentbackend.transformer.PatentTransformer.generateHTMLPatent(zahtevZaPriznanjePatenta);
//        return true;
//    }
//
//    public boolean generatePDF(com.example.patentbackend.dto.NazivPrijaveDTO brojPrijave) {
//        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getZahtevZaPriznanjePatenta(brojPrijave.getNaziv());
//        if (zahtevZaPriznanjePatenta == null) {
//            return false;
//        }
//        com.example.patentbackend.transformer.PatentTransformer.generatePDFPatent(zahtevZaPriznanjePatenta);
//        return true;
//    }
}
