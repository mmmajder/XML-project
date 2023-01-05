package com.example.patentbackend.transformer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.itextpdf.text.DocumentException;
import org.w3c.dom.Node;
//import com.itextpdf.tool.xml.XMLWorkerHelper;


/**
 * Primer demonstrira koriscenje iText PDF programskog API-a za
 * renderovanje PDF-a na osnovu XML dokumenta. Alternativa Apache FOP-u.
 */
public class PatentTransformer {


    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    //	public static final String INPUT_FILE = "resources/pdf/bookstore.xml";
//
    public static final String XSL_FILE = "src/main/java/com/example/patentbackend/transformer/Zahtev.xsl";

    public static final String HTML_FOLDER = "resources/gen/xhtml/";

    public static final String PDF_FOLDER = "resources/gen/pdf/";

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }


    public static void generateHTML(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {

        File pdfFile = new File(PDF_FOLDER + zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave());

        if (!pdfFile.getParentFile().exists()) {
            System.out.println("[INFO] A new directory is created: " + pdfFile.getParentFile().getAbsolutePath() + ".");
            pdfFile.getParentFile().mkdir();
        }

        Transformer pdfTransformer = new Transformer();

        try {
            pdfTransformer.generateHTML(zahtevZaPriznanjePatenta, XSL_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a PDF using iText Java API
     *
     * @param filePath
     * @throws IOException
     * @throws DocumentException
     */
//    public void generatePDF(String filePath) throws IOException, DocumentException {
//
//    	// Step 1
//    	Document document = new Document();
//
//    	// Step 2
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
//
//        // Step 3
//        document.open();
//
//        // Step 4
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML_FILE));
//
//        // Step 5
//        document.close();
//
//    }
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

    private void generateHTML(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta, String xslPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource((Node) zahtevZaPriznanjePatenta);
            StreamResult result = new StreamResult(new FileOutputStream(HTML_FOLDER + zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave()));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
