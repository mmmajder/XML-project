package com.example.zigbackend.transformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


public class ZigTransformer {
	private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String PDF_XSL_FILE = "src/main/java/com/example/zigbackend/transformer/Zig_zahtev_pdf.xsl";
    public static final String HTML_XSL_FILE = "src/main/java/com/example/zigbackend/transformer/Zig_zahtev_html.xsl";

    private static final String filePathForGeneratedFiles = "C:\\Faks\\VII\\XML i veb servisi\\XML-project\\generatedFiles\\";
    public static final String HTML_FOLDER = filePathForGeneratedFiles;//"data/gen/xhtml/";

    public static final String PDF_FOLDER = filePathForGeneratedFiles;//"data/gen/pdf/";
    private static final String TARGET_NAMESPACE = "com.example.zigbackend.model"; // koriscenje namespace-a, a ne .class resilo problem

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();
    }
    
    public static String generateHTMLZig(ZahtevZaPriznanjeZiga zahtev) {
        String filename = null;

        try {
            filename = ZigTransformer.generateHTML(zahtev, HTML_XSL_FILE, false);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("IZGENERISAO HTML " + filename);
        return filename;
    }

    public static String generatePDFZig(ZahtevZaPriznanjeZiga zahtev) {
        String titlePdf = null;

        try {
        	ZigTransformer.generateHTML(zahtev, PDF_XSL_FILE, true);
            titlePdf = getSupposedFileName(zahtev.getBrojPrijaveZiga()) + ".pdf";
            String titleHTML = getSupposedFileName(zahtev.getBrojPrijaveZiga()) + "_for_pdf" + ".html";
            ZigTransformer.generatePDF(titlePdf, titleHTML);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("IZGENERISAO PDF " + titlePdf);
        return titlePdf;
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
    
    private static String generateHTML(ZahtevZaPriznanjeZiga zahtev, String xslPath, boolean forPdf) throws FileNotFoundException {
        String fileName = null;

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML

            JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
            JAXBSource source = new JAXBSource(context, zahtev);
            StreamResult result;

            if (forPdf) {
                fileName = HTML_FOLDER + getSupposedFileName(zahtev.getBrojPrijaveZiga()) + "_for_pdf" + ".html";
            } else {
                fileName = HTML_FOLDER + getSupposedFileName(zahtev.getBrojPrijaveZiga()) + ".html";
            }

            result = new StreamResult(new FileOutputStream(fileName));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public static String getSupposedFileName(String brojPrijave){
        return "zig_" + brojPrijave.replace('/', '_');
    }
}
