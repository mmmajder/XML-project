package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.InformacijeOZahtevu;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import com.example.autorskapravabackend.transformer.AutorskaPravaTransformer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class AutorskaPravaService {

    private final AutorskaPravaRepository autorskaPravaRepository = new AutorskaPravaRepository();

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return autorskaPravaRepository.getZahtev(brojPrijave);
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
