package com.example.zigbackend.fuseki;

import com.example.zigbackend.dto.IzvestajRequest;
import com.example.zigbackend.dto.MetadataSearchParams;
import com.example.zigbackend.model.EStatus;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.repository.ZigRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSetRewindable;
import org.apache.jena.rdf.model.Model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class ZigFusekiDB {

    public static void save(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws IOException {
        Model model = ZigMetadataExtractor.extract(zahtevZaPriznanjeZiga);
        FusekiWriter.saveRdfGraphToDatabase(model);
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
            ZigRepository zigRepository = new ZigRepository();
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getStart());
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(izvestajRequest.getEnd());

            for (ZahtevZaPriznanjeZiga zahtev : zigRepository.getAllByStatus(EStatus.ODBIJENO)) {
                Date predato = getDateFromString(zahtev.getDatumPodnosenja());
                if (predato.before(end) && predato.after(start)) {
                    odbijeniZahtevi += 1;
                }
            }

            for (ZahtevZaPriznanjeZiga zahtev : zigRepository.getAllByStatus(EStatus.PRIHVACENO)) {
                Date predato = getDateFromString(zahtev.getDatumPodnosenja());
                if (predato.before(end) && predato.after(start)) {
                    prihvaceniZahtevi += 1;
                }
            }

            for (ZahtevZaPriznanjeZiga zahtev : zigRepository.getAllByStatus(EStatus.PREDATO)) {
                Date predato = getDateFromString(zahtev.getDatumPodnosenja());
                if (predato.before(end) && predato.after(start)) {
                    zahtevi += 1;
                }
            }
            zahtevi += prihvaceniZahtevi;
            zahtevi += odbijeniZahtevi;
        } catch (Exception ignore) {
        }

        String fileName = createDocument(zahtevi, prihvaceniZahtevi, odbijeniZahtevi, izvestajRequest);
        File file = new File("src/main/resources/generatedFiles/izvestaji/" + fileName);

        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    }

    private static String createDocument(int brojZahteva, int prihvaceniZahtevi, int odbijeniZahtevi, IzvestajRequest izvestajRequest) throws FileNotFoundException, DocumentException {
        String fileName = "izvestaj_" + izvestajRequest.toString() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/generatedFiles/izvestaji/" + fileName));
        document.open();

        document.add(new Paragraph("IZVEŠTAJ"));
        document.add(new Paragraph("\nPocetni datum: " + izvestajRequest.getStart()));
        document.add(new Paragraph("\nPocetni datum: " + izvestajRequest.getEnd()));
        document.add(new Paragraph("\nTip dela: Zahtev za priznanje žiga"));
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

    private static Date getDateFromString(String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.");
        Date date = null;

        try {
            date = formatter.parse(dateStr);
        }
        catch (ParseException except) {
            except.printStackTrace();
        }

        return date;
     }
}
