package com.example.zigbackend.service;

import com.example.zigbackend.dto.DetaljiOZahtevu;
import com.example.zigbackend.dto.ObradaZahteva;
import com.example.zigbackend.dto.SimpleUserDTO;
import com.example.zigbackend.dto.SimpleZahtevDTO;
import com.example.zigbackend.mapper.ZigMapper;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.repository.ResenjeZahtevaRepository;
import com.example.zigbackend.resenje.ResenjeZahteva;
import com.example.zigbackend.transformer.ZigTransformer;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ResenjeService {
    private final ResenjeZahtevaRepository repository = new ResenjeZahtevaRepository();
    private final ZigService service;

    public ResenjeService(ZigService service) {
        this.service = service;
    }

    public DetaljiOZahtevu getResenjeZahteva(String brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResenjeZahteva resenje = repository.dobaviPoBrojuPrijave(brojPrijave);
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = service.getZahtev(brojPrijave);

        SimpleZahtevDTO zahtevDTO = ZigMapper.mapToSimpleZahtev(zahtevZaPriznanjeZiga);
        SimpleUserDTO sluzbenik = ZigMapper.mapToSimpleUser(zahtevZaPriznanjeZiga.getPodnosilacPrijave());

        ObradaZahteva obrada = ZigMapper.mapToObradaZahteva(resenje, sluzbenik);

        DetaljiOZahtevu detaljiOZahtevu = new DetaljiOZahtevu();
        detaljiOZahtevu.setZahtev(zahtevDTO);
        detaljiOZahtevu.setObrada(obrada);

        return detaljiOZahtevu;
    }

    public void obradiZahtev(ObradaZahteva obradaZahteva) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException {
        ResenjeZahteva resenjeZahteva = new ResenjeZahteva();
        resenjeZahteva.setBrojPrijave(obradaZahteva.getBrojPrijave());
        resenjeZahteva.setImeSluzbenika(obradaZahteva.getSluzbenik().getName());
        resenjeZahteva.setEmailSluzbenika(obradaZahteva.getSluzbenik().getEmail());
        Date now = new Date(System.currentTimeMillis());
        resenjeZahteva.setDatumObrade(mapDateToString(now));
        resenjeZahteva.setOdbijen(obradaZahteva.isOdbijen());

        if (resenjeZahteva.isOdbijen()){
            resenjeZahteva.setRazlogOdbijanja(obradaZahteva.getRazlogOdbijanja());
            service.declineZahtev(obradaZahteva.getBrojPrijave());
        }
        else {
            resenjeZahteva.setSifra(obradaZahteva.getBrojPrijave() + "_" + ZigMapper.mapDateToString(new Date()).replace('.', '_'));
            service.acceptZahtev(obradaZahteva.getBrojPrijave());
        }
        repository.kreiraj(resenjeZahteva);
    }

    private String getPodnosilacEmail(ResenjeZahteva resenjeZahteva) {
        return service.getZahtev(resenjeZahteva.getBrojPrijave()).getPodnosilacPrijave().getKontakt().getEmail();
    }

    public String generatePDF(ResenjeZahteva resenjeZahteva) {
        try {
            String title = "resenje_" + resenjeZahteva.getBrojPrijave().replace("/", "_");
            ZigTransformer.generateResenjePDF(resenjeZahteva, title);
            return title;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateResenje(String brojPrijave) {
        try {
            ResenjeZahteva resenjeZahteva = repository.dobaviPoBrojuPrijave(brojPrijave);
            if (resenjeZahteva == null) {
                throw new NotFoundException("Resenje ne postoji.");
            }
            String title = generatePDF(resenjeZahteva);
            File pdfFile = new File("src/main/resources/generatedFiles/resenja/" + title + ".pdf");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String mapDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");

        return dateFormat.format(date);
    }
}
