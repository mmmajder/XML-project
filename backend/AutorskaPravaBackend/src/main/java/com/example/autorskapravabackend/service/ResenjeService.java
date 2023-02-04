package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.DetaljiOZahtevu;
import com.example.autorskapravabackend.dto.ObradaZahteva;
import com.example.autorskapravabackend.dto.SimpleUser;
import com.example.autorskapravabackend.dto.ZahtevDTO;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import com.example.autorskapravabackend.transformer.AutorskaPravaTransformer;
import com.example.autorskapravabackend.utils.Utils;
import com.example.autorskapravabackend.repository.ResenjeZahtevaRepository;
import com.itextpdf.text.DocumentException;
import jakarta.mail.MessagingException;
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
    private final AutorskaPravaService service;
    private final EmailService emailService;

    public ResenjeService(AutorskaPravaService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    public DetaljiOZahtevu getResenjeZahteva(String brojPrijave) throws XMLDBException, IOException, JAXBException {
        ResenjeZahteva resenje = repository.dobaviPoBrojuPrijave(brojPrijave);
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = service.getZahtev(brojPrijave);

        ZahtevDTO zahtevDTO = ZahtevDTO.builder()
                .datumPodnosenja(Utils.formatDate(zahtevZaAutorskaPrava.getInformacijeOZahtevu().getDatumPodnosenja()))
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

    public void obradiZahtev(ObradaZahteva obradaZahteva) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException, MessagingException {
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

        service.setObradjen(obradaZahteva.getBrojPrijave(), obradaZahteva.isOdbijen());

        System.out.println("SLANJE EMAIL-a podnosiocu zahteva");
        emailService.sendMailWithAttachment(getPodnosilacEmail(resenjeZahteva), resenjeZahteva, generatePDF(resenjeZahteva));
        System.out.println("EMAIL SA RESENJEM ZAHTEVA POSLAT");
    }

    private String getPodnosilacEmail(ResenjeZahteva resenjeZahteva) {
        return service.getZahtev(resenjeZahteva.getBrojPrijave()).getSadrzajZahteva().getPodnosilacZahteva().getPodaciOPodnosiocu().getEmail();
    }

    public String generatePDF(ResenjeZahteva resenjeZahteva) {
        try {
            String title = "resenje_" + resenjeZahteva.getBrojPrijave().replace("/", "_");
            AutorskaPravaTransformer.generateResenjePDF(resenjeZahteva, title);
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
