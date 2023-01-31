package com.example.patentbackend.transformer;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
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


    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }


    public static void generateHTMLPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            PatentTransformer.generateHTML(zahtevZaPriznanjePatenta, HTML_XSL_FILE, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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


}
