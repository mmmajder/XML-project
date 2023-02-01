package com.example.autorskapravabackend.rdf;

import com.example.autorskapravabackend.db.ResenjeZahtevaDB;
import com.example.autorskapravabackend.dto.IzvestajRequest;
import com.example.autorskapravabackend.dto.MetadataSearchParams;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import static com.example.autorskapravabackend.utils.Utils.dateToXMLGregorian;
import static com.example.autorskapravabackend.utils.Utils.stringToXMLGregorian;

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

    public static ByteArrayInputStream generateReport(IzvestajRequest izvestajRequest) throws DatatypeConfigurationException, ParseException {
        int zahtevi = 0;
        int prihvaceniZahtevi = 0;
        int odbijeniZahtevi = 0;

        XMLGregorianCalendar pocetak = stringToXMLGregorian(izvestajRequest.getStart());
        XMLGregorianCalendar kraj = stringToXMLGregorian(izvestajRequest.getEnd());

        String queryString = "select ?s ?p ?o {graph ?g {?s ?p ?o}}";
        Query query = QueryFactory.create(queryString);

        try (QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:8080/fuseki/AutorskaPravaData/", query)) {
            ResultSet resultSet = qexec.execSelect();

            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.next();
                String p = solution.get("p").toString();
                String o = solution.get("o").toString();
                if (p.contains("datum_podnosenja")) {
                    XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(o);
                    if (pocetak.compare(date) <= 0 && kraj.compare(date) >= 0) {
                        zahtevi++;
                    }
                }
            }

            for (ResenjeZahteva resenjeZahteva : ResenjeZahtevaDB.dobaviSvaResenja()) {
                XMLGregorianCalendar datumObrade = dateToXMLGregorian(resenjeZahteva.getDatumObrade());
                if (pocetak.compare(datumObrade) <= 0 && kraj.compare(datumObrade) >= 0) {
                    if (resenjeZahteva.isOdbijen()) {
                        odbijeniZahtevi++;
                    } else {
                        prihvaceniZahtevi++;
                    }
                }
            }
            String fileName = createDocument(zahtevi, prihvaceniZahtevi, odbijeniZahtevi, izvestajRequest);
            File file = new File("src/main/resources/gen/izvestaji/" + fileName);

            return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String createDocument(int brojZahteva, int prihvaceniZahtevi, int odbijeniZahtevi, IzvestajRequest izvestajRequest) throws FileNotFoundException, DocumentException {
        String fileName = "izvestaj_" + izvestajRequest.toString() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/gen/izvestaji/" + fileName));
        document.open();

        document.add(new Chunk("IZVESTAJ"));
        document.add(new Chunk("Pocetni datum: " + izvestajRequest.getStart()));
        document.add(new Chunk("Pocetni datum: " + izvestajRequest.getEnd()));
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
