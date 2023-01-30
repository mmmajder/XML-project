package com.example.zigbackend.transformer;

import com.example.zigbackend.resenje.ResenjeZahteva;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ResenjeTransformer {

    public static String generatePdf(ResenjeZahteva resenjeZahteva) throws IOException, DocumentException {
        String title = "resenjeAutorskogPrava_" + resenjeZahteva.getBrojPrijave().replace('/', '_') + ".pdf";

        Document document = new Document();
        File file = new File("src/main/resources/gen/resenjaPDF/" + title);
        if (file.createNewFile()) {
            System.out.println("PDF created.");
        } else {
            System.out.println("PDF already exists.");
        }
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font textFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Font redTextFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.RED);
        Font greenTextFont = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.GREEN);
        document.add(new Chunk("Rešenje zahteva za autorsko pravo " + resenjeZahteva.getBrojPrijave(), titleFont));

        if (resenjeZahteva.isOdbijen()) {
            document.add(new Chunk("\nZahtev je ODBIJEN", redTextFont));
            document.add(new Chunk("\nRazlog odbijanja: " + resenjeZahteva.getRazlogOdbijanja(), textFont));
        } else {
            document.add(new Chunk("\nZahtev je PRIHVAĆEN", greenTextFont));
        }

        document.add(new Chunk("\nDatum obrade zahteva: " + resenjeZahteva.getBrojPrijave(), textFont));
        document.add(new Chunk("\nSlužbenik koji je obradio zahtev: " + resenjeZahteva.getImeSluzbenika() + " " + resenjeZahteva.getPrezimeSluzbenika(), textFont));
        document.add(new Chunk("\nUkoliko imate bilo kakvih pitanja, možete se obraditi službeniku na email " + resenjeZahteva.getEmailSluzbenika(), textFont));

        document.close();
        return title;
    }
}
