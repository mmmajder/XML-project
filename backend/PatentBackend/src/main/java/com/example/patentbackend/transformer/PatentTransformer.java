package com.example.patentbackend.transformer;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.resenje.ResenjeZahteva;
import com.example.patentbackend.utils.Utils;
import com.itextpdf.text.DocumentException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * Primer demonstrira koriscenje iText PDF programskog API-a za
 * renderovanje PDF-a na osnovu XML dokumenta. Alternativa Apache FOP-u.
 */
public class PatentTransformer {


    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    //	public static final String INPUT_FILE = "resources/pdf/bookstore.xml";
//
    public static final String PDF_XSL_FILE = "src/main/java/com/example/patentbackend/transformer/Zahtev_PDF.xsl";
    public static final String HTML_XSL_FILE = "src/main/java/com/example/patentbackend/transformer/Zahtev_HTML.xsl";
    public static final String HTML_FOLDER = "src/main/resources/gen/xhtml/";
    public static final String PDF_FOLDER = "src/main/resources/gen/pdf/";
    public static final String XSL_RESENJE_FILE = "src/main/java/com/example/patentbackend/transformer/Resenje.xsl";
    public static final String HTML_RESENJA_FOLDER = "src/main/resources/gen/resenjaHTML/";
    public static final String PDF_RESENJA_FOLDER = "src/main/resources/gen/resenjaPDF/";


    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }

    private static String getFileTitle(ZahtevZaPriznanjePatenta zahtev) {
        return "patent" + zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave().replace('/', '_');
    }

    public static void generateZahtevPDF(ZahtevZaPriznanjePatenta zahtev) {
        try {
            PatentTransformer.generateZahtevHTML(zahtev, true);
            String title = getFileTitle(zahtev);
            String titlePdf = title + ".pdf";
            String titleHTML = title + "_for_pdf.html";
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FOLDER + titlePdf));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FOLDER + titleHTML));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateHTMLPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            PatentTransformer.generateHTML(zahtevZaPriznanjePatenta, HTML_XSL_FILE, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateZahtevHTML(ZahtevZaPriznanjePatenta zahtev, boolean forPdf) {
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
            JAXBContext context = JAXBContext.newInstance("com.example.patentbackend.model");
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

    public static void generatePDFPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            PatentTransformer.generateHTML(zahtevZaPriznanjePatenta, PDF_XSL_FILE, true);
            String titlePdf = Utils.formatNameOfRequestForPatent(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), ".pdf");
            String titleHTML = Utils.formatNameOfRequestForPatent(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), "_for_pdf.html");
            PatentTransformer.generatePDF(titlePdf, titleHTML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generatePDF(String titlePdf, String titleHTML) throws IOException, DocumentException {

        // Step 1
        Document document = new Document();

        // Step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_FOLDER + titlePdf));

        // Step 3
        document.open();

        // Step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FOLDER + titleHTML));

        // Step 5
        document.close();

    }

    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    private static void generateHTML(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta, String xslPath, boolean forPdf) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML

            JAXBContext context = JAXBContext.newInstance("com.example.patentbackend.model");
            JAXBSource source = new JAXBSource(context, zahtevZaPriznanjePatenta);
            StreamResult result;

            if (forPdf) {
                result = new StreamResult(new FileOutputStream(HTML_FOLDER + Utils.formatNameOfRequestForPatent(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), "_for_pdf.html")));
            } else {
                result =  new StreamResult(new FileOutputStream(HTML_FOLDER + Utils.formatNameOfRequestForPatent(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave(), ".html")));
            }

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void generateResenjePDF(ResenjeZahteva resenjeZahteva, String title) {
        try {
            PatentTransformer.generateResenjeHTML(resenjeZahteva, title);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF_RESENJA_FOLDER + title + ".pdf"));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_RESENJA_FOLDER + title + ".html"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateResenjeHTML(ResenjeZahteva resenjeZahteva, String title) {
        try {
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(XSL_RESENJE_FILE));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "gen/xhtml");

            // Transform DOM to HTML
            JAXBContext context = JAXBContext.newInstance("com.example.patentbackend.resenje");
            JAXBSource source = new JAXBSource(context, resenjeZahteva);
            StreamResult result = new StreamResult(new FileOutputStream(HTML_RESENJA_FOLDER + title + ".html"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
