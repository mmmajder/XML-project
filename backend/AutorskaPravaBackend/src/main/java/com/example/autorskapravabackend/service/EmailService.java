package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    public void sendMailWithAttachment(String emailPodnosioca, ResenjeZahteva resenjeZahteva, String resenjePDF) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailPodnosioca));
            mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
            mimeMessage.setSubject(getEmailSubject(resenjeZahteva.getBrojPrijave()));
            mimeMessage.setText(getEmailBody(resenjeZahteva.isOdbijen()));

            FileSystemResource file = new FileSystemResource(new File(resenjePDF));
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.addAttachment("resenje.pdf", file);
        };

        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
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