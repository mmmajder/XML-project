package com.example.patentbackend.rdf;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.example.patentbackend.dto.IzvestajRequest;
import com.example.patentbackend.dto.MetadataSearchParams;
import com.example.patentbackend.model.*;
import com.example.patentbackend.repository.PatentRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;

public class PatentFusekiDB {
    public static void save(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        System.out.println("[INFO] Marshalling customized address book:");
        Model metadataModel = PatentMetadataExtractor.extract(zahtevZaPriznanjePatenta);
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
            PatentRepository patentRepository = new PatentRepository();
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getStart());
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getEnd());

            for (ZahtevZaPriznanjePatenta zahtev : patentRepository.getAllCanceled()) {
                Date predato = zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    odbijeniZahtevi += 1;
                }
            }

            for (ZahtevZaPriznanjePatenta zahtev : patentRepository.getAllAccepted()) {
                Date predato = zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    prihvaceniZahtevi += 1;
                }
            }

            for (ZahtevZaPriznanjePatenta zahtev : patentRepository.getAllPending()) {
                Date predato = zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja();
                if (predato.before(end) && predato.after(start)) {
                    zahtevi += 1;
                }
            }
            zahtevi += prihvaceniZahtevi;
            zahtevi += odbijeniZahtevi;
        } catch (Exception ignore) {
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
        document.add(new Paragraph("\nTip dela: Zahtev za patent"));
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
