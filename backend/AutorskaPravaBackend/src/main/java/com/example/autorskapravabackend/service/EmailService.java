package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;
    private final Environment env;

    @Async
    public void sendMailWithAttachment(String emailPodnosioca, ResenjeZahteva resenjeZahteva, String resenjePDF) throws MailException, MessagingException {
        System.out.println("Sending email...");
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject(getEmailSubject(resenjeZahteva.getBrojPrijave()));
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("ubernet-test@outlook.com");
        helper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        helper.setText(getEmailBody(resenjeZahteva.isOdbijen()), true);

        String path = "src/main/resources/gen/resenjaPDF/";
        FileSystemResource file = new FileSystemResource(new File(path + resenjePDF + ".pdf"));
        helper.addAttachment("resenje.pdf", file);

        mailSender.send(message);
        System.out.println("Email sent!");
    }

    private String getEmailSubject(String brojPrijave) {
        return "Zahtev za autosko pravo broj " + brojPrijave + " je obrađen";
    }

    private String getEmailBody(boolean odbijen) {
        if (odbijen) {
            return """
                    Poštovani,
                                        
                    Vaš zahtev za autorsko pravo nad delom je nažalost ODBIJEN. Razlog odbijanja možete pogledati u priloženom dokumentu.
                                        
                    Ukoliko imate bilo kakvih pitanja, budite slobodni da kontaktirate nas ili službenika na email koji vidite u dokumentu.
                                        
                    Srdačan pozdrav,
                    Krindž""";
        }
        return """
                Poštovani,
                                    
                Vaš zahtev za autorsko pravo nad delom je PRIHVAĆEN, čestitamo! Detalje o rešenju zahteva možete pročitati u priloženom dokumentu. 
                                    
                Ukoliko imate bilo kakvih pitanja, budite slobodni da kontaktirate nas ili službenika na email koji vidite u dokumentu.
                                    
                Srdačan pozdrav,
                Krindž""";
    }
}