package com.example.autorskapravabackend.transformer;

import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class AutorskaPravaTransformer {

    private static final TransformerFactory transformerFactory;

    public static final String PDF_XSL_FILE = "src/main/java/com/example/autorskapravabackend/transformer/Zahtev_A_PDF.xsl";
    public static final String HTML_XSL_FILE = "src/main/java/com/example/autorskapravabackend/transformer/Zahtev_A_HTML.xsl";

    public static final String HTML_FOLDER = "src/main/resources/gen/xhtml/";

    public static final String PDF_FOLDER = "src/main/resources/gen/pdf/";

    static {
        /* Inicijalizacija DOM fabrike */
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }

    private static String getFileTitle(ZahtevZaAutorskaPrava zahtev) {
        return "autorskaPrava_" + zahtev.getInformacijeOZahtevu().getBrojPrijave().replace('/', '_');
    }

    public static void generatePDF(ZahtevZaAutorskaPrava zahtev) {
        try {
            AutorskaPravaTransformer.generateHTML(zahtev, true);
            String title = getFileTitle(zahtev);
            String titlePdf = title + ".pdf";
            String titleHTML = title + "_for_pdf.html";
            AutorskaPravaTransformer.generatePDF(titlePdf, titleHTML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generatePDF(String titlePdf, String titleHTML) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FOLDER + titlePdf));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FOLDER + titleHTML));
        document.close();
    }

    public static void generateHTML(ZahtevZaAutorskaPrava zahtev, boolean forPdf) {
        try {
            String xslFile;

            if (forPdf){
                xslFile = PDF_XSL_FILE;
            } else {
                xslFile = HTML_XSL_FILE;
            }

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslFile));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "gen/xhtml");

            // Transform DOM to HTML
            JAXBContext context = JAXBContext.newInstance("com.example.autorskapravabackend.model");
            JAXBSource source = new JAXBSource(context, zahtev);
            StreamResult result;

            if (forPdf) {
                result = new StreamResult(new FileOutputStream(HTML_FOLDER + getFileTitle(zahtev) + "_for_pdf" + ".html"));
            } else {
                result = new StreamResult(new FileOutputStream(HTML_FOLDER + getFileTitle(zahtev) + ".html"));
            }

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
