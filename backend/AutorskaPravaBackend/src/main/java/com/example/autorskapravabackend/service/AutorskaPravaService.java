package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.InformacijeOZahtevu;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import com.example.autorskapravabackend.transformer.AutorskaPravaTransformer;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
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

    public ByteArrayInputStream generateHTML(String brojPrijave) {
        try {
            AutorskaPravaTransformer.generateZahtevHTML(getZahtev(brojPrijave));
            File htmlFile = new File("src/main/resources/gen/xhtml/autorskaPrava_" + brojPrijave.replace('/', '_') + ".html");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(htmlFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generatePDF(String brojPrijave) {
        try {
            AutorskaPravaTransformer.generateZahtevPDF(getZahtev(brojPrijave));
            File pdfFile = new File("src/main/resources/gen/pdf/autorskaPrava_" + brojPrijave.replace('/', '_') + ".pdf");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateRDF(String brojPrijave) {
        try {
            String rdf = autorskaPravaRepository.generateRDF(brojPrijave);
            return new ByteArrayInputStream(rdf.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateJSON(String brojPrijave) {
        try {
            String json = autorskaPravaRepository.generateJSON(brojPrijave);
            return new ByteArrayInputStream(json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
