package com.example.autorskapravabackend.rdf;

import com.example.autorskapravabackend.dto.IzvestajRequest;
import com.example.autorskapravabackend.dto.MetadataSearchParams;
import com.example.autorskapravabackend.model.EStatus;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class AutorskaPravaFusekiDB {
    public static void save(ZahtevZaAutorskaPrava zahtev) {
        System.out.println("[INFO] Marshalling customized address book:");
        Model metadataModel = AutorskaPravaMetadataExtractor.extract(zahtev);
        try {
            FusekiWriter.saveRdfGraphToDatabase(metadataModel);
            FusekiReader.readAllDataFromDatabase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> findByMetadata(MetadataSearchParams param) throws IOException {
        return FusekiReader.findByMetadata(param);
    }

    public static List<String> findByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        return FusekiReader.findByMetadata(params);
    }

    public static String getRdfString(String brojPrijave) throws Exception {
        return FusekiReader.getRdfString(brojPrijave);
    }

    public static String getJsonString(String brojPrijave) throws Exception {
        return FusekiReader.getJsonString(brojPrijave);
    }

    public static ByteArrayInputStream generateReport(IzvestajRequest izvestajRequest) throws IOException, DocumentException {
        int zahtevi = 0;
        int prihvaceniZahtevi = 0;
        int odbijeniZahtevi = 0;

        try {
            AutorskaPravaRepository autorskaPravaRepository = new AutorskaPravaRepository();
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getStart());
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getEnd());
            System.out.println("START: " + izvestajRequest.getStart());
            System.out.println("END: " + izvestajRequest.getEnd());

            for (ZahtevZaAutorskaPrava zahtev : autorskaPravaRepository.getAllByStatus(EStatus.ODBIJENO)) {
                System.out.println("BROJ PRIJAVE ODBIJENI: " + zahtev.getInformacijeOZahtevu().getBrojPrijave());
                Date predato = zahtev.getInformacijeOZahtevu().getDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    odbijeniZahtevi += 1;
                }
            }

            for (ZahtevZaAutorskaPrava zahtev : autorskaPravaRepository.getAllByStatus(EStatus.PRIHVACENO)) {
                System.out.println("BROJ PRIJAVE PRIHVACENO: " + zahtev.getInformacijeOZahtevu().getBrojPrijave());
                Date predato = zahtev.getInformacijeOZahtevu().getDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    prihvaceniZahtevi += 1;
                }
            }

            for (ZahtevZaAutorskaPrava zahtev : autorskaPravaRepository.getAllByStatus(EStatus.PREDATO)) {
                System.out.println("BROJ PRIJAVE PREDATO: " + zahtev.getInformacijeOZahtevu().getBrojPrijave());
                Date predato = zahtev.getInformacijeOZahtevu().getDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    zahtevi += 1;
                }
            }
            zahtevi += prihvaceniZahtevi;
            zahtevi += odbijeniZahtevi;
        } catch (Exception ignore) {
            System.out.println("PUKAO JE");
        }

        String fileName = createDocument(zahtevi, prihvaceniZahtevi, odbijeniZahtevi, izvestajRequest);
        File file = new File("src/main/resources/gen/izvestaji/" + fileName);

        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    }

    private static String createDocument(int brojZahteva, int prihvaceniZahtevi, int odbijeniZahtevi, IzvestajRequest izvestajRequest) throws FileNotFoundException, DocumentException {
        String fileName = "izvestaj_" + izvestajRequest.toString() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/gen/izvestaji/" + fileName));
        document.open();

        document.add(new Paragraph("IZVESTAJ"));
        document.add(new Paragraph("\nPocetni datum: " + izvestajRequest.getStart()));
        document.add(new Paragraph("\nPocetni datum: " + izvestajRequest.getEnd()));
        document.add(new Paragraph("\nTip dela: Zahtev za autorsko pravo"));
        document.add(new Paragraph("\n\n"));

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table, brojZahteva, prihvaceniZahtevi, odbijeniZahtevi);
        document.add(table);
        document.close();

        return fileName;
    }

    private static void addRows(PdfPTable table, int zahtevi, int prihvaceniZahtevi, int odbijeniZahtevi) {
        table.addCell(String.valueOf(zahtevi));
        table.addCell(String.valueOf(prihvaceniZahtevi));
        table.addCell(String.valueOf(odbijeniZahtevi));
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("Broj podnetih zahteva", "Broj prihvacenih zahteva", "Broj odbijenih zahteva")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

}
