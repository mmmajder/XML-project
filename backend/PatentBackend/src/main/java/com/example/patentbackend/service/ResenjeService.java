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
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;
import java.util.Date;

@Service
public class ResenjeService {
    private final ResenjeZahtevaRepository repository = new ResenjeZahtevaRepository();
    private final PatentService service;

    public ResenjeService(PatentService service) {
        this.service = service;
    }

    public DetaljiOZahtevu getResenjeZahteva(String brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResenjeZahteva resenje = repository.dobaviPoBrojuPrijave(brojPrijave);
        ZahtevZaPriznanjePatenta zahtev = service.getZahtev(brojPrijave);

        ZahtevDTO zahtevDTO = ZahtevDTO.builder()
                .datumPodnosenja(Utils.formatDate(zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja()))
                .brojPrijave(brojPrijave)
                .build();
        SimpleUser sluzbenik = SimpleUser.builder()
                .name(resenje.getImeSluzbenika() + " " + resenje.getPrezimeSluzbenika())
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
}
