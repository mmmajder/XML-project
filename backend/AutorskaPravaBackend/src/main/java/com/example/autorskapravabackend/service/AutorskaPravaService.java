package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.*;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.InformacijeOZahtevu;
import com.example.autorskapravabackend.model.ResenjeZahteva;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import com.example.autorskapravabackend.transformer.AutorskaPravaTransformer;
import com.example.autorskapravabackend.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AutorskaPravaService {

    private final AutorskaPravaRepository autorskaPravaRepository = new AutorskaPravaRepository();

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return autorskaPravaRepository.getZahtev(brojPrijave);
    }

    public List<ZahtevZaAutorskaPrava> getZahtevi() {
        return autorskaPravaRepository.getZahtevi();
    }

    public ZahtevZaAutorskaPrava createZahtevZaAutorskaPrava(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = Mapper.dtoToZahtev(dto);
        setBrojPrijave(zahtevZaAutorskaPrava);
        autorskaPravaRepository.createRequest(zahtevZaAutorskaPrava);
        System.out.println(getZahtev(zahtevZaAutorskaPrava.getInformacijeOZahtevu().getBrojPrijave()));
        return zahtevZaAutorskaPrava;
    }

    private void setBrojPrijave(ZahtevZaAutorskaPrava zahtev) {
        InformacijeOZahtevu osnovneInformacije = new InformacijeOZahtevu();
        String brojPrijave = "A-" + LocalDate.now().getYear() + "/" + (autorskaPravaRepository.getNumberOfRequests() + 1);
        osnovneInformacije.setBrojPrijave(brojPrijave);
        osnovneInformacije.setDatumPodnosenja(new Date());
        zahtev.setInformacijeOZahtevu(osnovneInformacije);
    }

    public boolean generateHTML(String brojPrijave) {
        ZahtevZaAutorskaPrava zahtev = getZahtev(brojPrijave);
        if (zahtev == null) {
            return false;
        }
        AutorskaPravaTransformer.generateHTML(zahtev);
        return true;
    }

    public boolean generatePDF(String brojPrijave) {
        ZahtevZaAutorskaPrava zahtev = getZahtev(brojPrijave);
        if (zahtev == null) {
            return false;
        }
        AutorskaPravaTransformer.generatePDF(zahtev);
        return true;
    }
}
