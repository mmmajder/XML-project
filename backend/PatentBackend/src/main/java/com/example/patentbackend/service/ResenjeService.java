package com.example.patentbackend.service;

import com.example.patentbackend.dto.DetaljiOZahtevu;
import com.example.patentbackend.dto.ObradaZahteva;
import com.example.patentbackend.dto.SimpleUser;
import com.example.patentbackend.dto.ZahtevDTO;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.repository.ResenjeZahtevaRepository;
import com.example.patentbackend.resenje.ResenjeZahteva;
import com.example.patentbackend.transformer.PatentTransformer;
import com.example.patentbackend.utils.Utils;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.exist.http.NotFoundException;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class ResenjeService {
    private final ResenjeZahtevaRepository repository = new ResenjeZahtevaRepository();
    private final PatentService patentService;

    public ResenjeService(PatentService service) {
        this.patentService = service;
    }

    public DetaljiOZahtevu getResenjeZahteva(String brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException {
        ResenjeZahteva resenje = repository.dobaviPoBrojuPrijave(brojPrijave);
        ZahtevZaPriznanjePatenta zahtev = patentService.getZahtev(brojPrijave);

        ZahtevDTO zahtevDTO = ZahtevDTO.builder()
                .datumPodnosenja(Utils.formatDate(zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja()))
                .brojPrijave(brojPrijave)
                .build();
        SimpleUser sluzbenik = SimpleUser.builder()
                .name(resenje.getImeSluzbenika())
                .email(resenje.getEmailSluzbenika())
                .build();
        ObradaZahteva obrada = ObradaZahteva.builder()
                .sluzbenik(sluzbenik)
                .datumObrade(Utils.formatDate(resenje.getDatumObrade()))
                .odbijen(resenje.isOdbijen())
                .razlogOdbijanja(resenje.getRazlogOdbijanja())
                .sifra(resenje.getSifra())
                .build();
        return DetaljiOZahtevu.builder()
                .obrada(obrada)
                .zahtev(zahtevDTO)
                .build();
    }

    public void obradiZahtev(ObradaZahteva obradaZahteva) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException {
        ResenjeZahteva resenjeZahteva = new ResenjeZahteva();
        resenjeZahteva.setBrojPrijave(obradaZahteva.getBrojPrijave());
        resenjeZahteva.setImeSluzbenika(obradaZahteva.getSluzbenik().getName());
        resenjeZahteva.setEmailSluzbenika(obradaZahteva.getSluzbenik().getEmail());
        resenjeZahteva.setDatumObrade(new Date());
        resenjeZahteva.setOdbijen(obradaZahteva.isOdbijen());
        if (resenjeZahteva.isOdbijen())
            resenjeZahteva.setRazlogOdbijanja(obradaZahteva.getRazlogOdbijanja());
        else
            resenjeZahteva.setSifra(obradaZahteva.getBrojPrijave() + "_" + Utils.formatDate(new Date()).replace('.', '_'));
        repository.kreiraj(resenjeZahteva);
        patentService.setObradjen(obradaZahteva.getBrojPrijave(), obradaZahteva.isOdbijen());
    }

    public String generatePDF(ResenjeZahteva resenjeZahteva) {
        try {
            String title = "resenje_" + resenjeZahteva.getBrojPrijave().replace("/", "_");
            PatentTransformer.generateResenjePDF(resenjeZahteva, title);
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
            File pdfFile = new File("src/main/resources/gen/resenjaPDF/" + title + ".pdf");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
