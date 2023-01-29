package com.example.zigbackend.service;

import com.example.zigbackend.dto.MetadataSearchParams;
import com.example.zigbackend.dto.MetadataSearchParamsDTO;
import com.example.zigbackend.dto.ZahtevZaPriznanjeZigaDTO;
import com.example.zigbackend.dto.ZahteviZaPriznanjeZigaDTO;
import com.example.zigbackend.mapper.ZigMapper;
import com.example.zigbackend.model.*;
import com.example.zigbackend.repository.ZigRepository;
import com.example.zigbackend.transformer.ZigTransformer;
import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Service
public class ZigService {
    private final String filePathForUploadedFiles = "C:\\Faks\\VII\\XML i veb servisi\\XML-project\\uploadedFiles\\";
    private final String filePathForGeneratedFiles = "C:\\Faks\\VII\\XML i veb servisi\\XML-project\\generatedFiles\\";

    private final String filePathForQRs = filePathForGeneratedFiles + "QR\\";
    private final String downloadPdfHttp = "http://localhost:8002/zig/download/pdf/"; // used for QR

    private final int osnovnaTaksa = 300;
    private final int taksaPoKlasi = 150;
    private final int taksaZaGrafickoResenje = 500;

    private static HashMap<String, ZahtevZaPriznanjeZiga> prilogUpdatingZahtevs = new HashMap<>();
    private final Lock prilogUpdatingZahtevsLock = new ReentrantLock();

    private ZigRepository zigRepository = new ZigRepository();

    public ZahtevZaPriznanjeZiga getZahtev(String brojPrijave) {
        return zigRepository.getZahtev(brojPrijave);
    }

    private List<ZahtevZaPriznanjeZiga> getZahtevi(List<String> ids){
        return zigRepository.getZahteviByBrojPrijave(ids);
    }

    public ZahtevZaPriznanjeZiga saveZahtev(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws JAXBException, IOException, XMLDBException {
        return zigRepository.save(zahtevZaPriznanjeZiga);
    }

    public ZahtevZaPriznanjeZiga createZahtevZaPriznanjeZiga(ZahtevZaPriznanjeZigaDTO zahtevZaPriznanjeZigaDTO) throws JAXBException, IOException, XMLDBException {
        System.out.println("zahtevZaPriznanjeZigaDTO");
        System.out.println(zahtevZaPriznanjeZigaDTO);
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = ZigMapper.createZahtev(zahtevZaPriznanjeZigaDTO);
        System.out.println("\n zahtevZaPriznanjeZiga");
        System.out.println(zahtevZaPriznanjeZiga);
        zahtevZaPriznanjeZiga.setBrojPrijaveZiga(getNewBrojPrijave());
        System.out.println("\n setBrojPrijaveZiga");
        System.out.println(zahtevZaPriznanjeZiga.getBrojPrijaveZiga());
        setTaksa(zahtevZaPriznanjeZiga);
        addQr(zahtevZaPriznanjeZiga);
        saveZahtev(zahtevZaPriznanjeZiga);
        System.out.println("\n sacuvao");

        return zahtevZaPriznanjeZiga;
    }

    private String getNewBrojPrijave(){
        int currentYear = LocalDate.now().getYear();
        int numberOfZahtevi = getAllForYear(currentYear).size() + 1;
        String brojPrijave = Integer.toString(numberOfZahtevi);

        while (brojPrijave.length() < 5){
            brojPrijave = "0".concat(brojPrijave);
        }

        brojPrijave = brojPrijave.concat("/").concat(getLastTwoYearChars(currentYear));

        return brojPrijave;
    }

    private void setTaksa(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        Taksa taksa = new Taksa();
        taksa.setOsnovnaTaksa(osnovnaTaksa);
        taksa.setTaksaZaSveKlase(taksaPoKlasi * zahtevZaPriznanjeZiga.getKlasa().size());
        taksa.setTaksaZaGrafickoResenje(taksaZaGrafickoResenje); // kasnije se postavi na 0, ukoliko se upload-uje izgled ziga
        zahtevZaPriznanjeZiga.setTaksa(taksa);
    }

    private String getLastTwoYearChars(int year){
        String yearStr = Integer.toString(year);
        Character y1 = yearStr.charAt(2);
        Character y2 = yearStr.charAt(3);

        return y1.toString().concat(y2.toString());
    }

    public List<XMLResource> getAllForYear(int year){
        return zigRepository.getAllForYear(Integer.toString(year));
    }

    public String generateHTML(String brojPrijave) {
        String filename = null;
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getZahtev(brojPrijave);

        if (zahtevZaPriznanjeZiga != null){
            filename = ZigTransformer.generateHTMLZig(zahtevZaPriznanjeZiga);
        }

        return filename;
    }

    public String generatePDF(String brojPrijave) {
        String filename = null;
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getZahtev(brojPrijave);

        if (zahtevZaPriznanjeZiga != null){
            filename = ZigTransformer.generatePDFZig(zahtevZaPriznanjeZiga);
        }

        return filename;
    }

    private String getSupposedFileName(String brojPrijave){
        return ZigTransformer.getSupposedFileName(brojPrijave);
    }

    public List<ZahtevZaPriznanjeZiga> getByText(String text, boolean casesensitive, boolean isSearchForNeobradjeni) throws Exception {
        List<String> searchWords = Arrays.asList(text.split(" "));

        if (searchWords.size() == 0){
            return null;
        }

        List<ZahtevZaPriznanjeZiga> zahtevi = zigRepository.getByText(searchWords, casesensitive);

        if (isSearchForNeobradjeni){
            return zahtevi.stream().filter(z -> z.getStatus() == EStatus.PREDATO).collect(Collectors.toList());
        } else {
            return zahtevi.stream().filter(z -> z.getStatus() != EStatus.PREDATO).collect(Collectors.toList());
        }
    }

    public List<ZahtevZaPriznanjeZiga> getByMetadata(List<MetadataSearchParams> params) throws IOException {
        if (params.size() == 1){
            return zigRepository.getByMetadata(params.get(0));
        } else {
            return zigRepository.getByMultipleMetadata(params);
        }
    }

    public List<ZahtevZaPriznanjeZiga> getAllApplied() throws JAXBException, XMLDBException {
        return zigRepository.getAllApplied();
    }

    public List<ZahtevZaPriznanjeZiga> getAllApproved() throws JAXBException, XMLDBException {
        return zigRepository.getAllApproved();
    }

    public List<ZahtevZaPriznanjeZiga> getAllCanceled() throws JAXBException, XMLDBException {
        return zigRepository.getAllCanceled();
    }

    public List<ZahtevZaPriznanjeZiga> getAllDenied() throws JAXBException, XMLDBException {
        return zigRepository.getAllDenied();
    }

    public ZahteviZaPriznanjeZigaDTO mapToZahtevi(List<ZahtevZaPriznanjeZiga> zahteviList){
        ZahteviZaPriznanjeZigaDTO zahtevi = new ZahteviZaPriznanjeZigaDTO();
        zahtevi.setZahtevi(zahteviList);

        return zahtevi;
    }

    public boolean isMetadataDTOEmpty(MetadataSearchParamsDTO metadataSearchParamsDTO){
        String strippedProperty = metadataSearchParamsDTO.getProperty().trim();
        String strippedValue = metadataSearchParamsDTO.getValue().trim();
        String strippedOperator = metadataSearchParamsDTO.getOperator().trim();

        return "".equals(strippedProperty) || "".equals(strippedValue) || "".equals(strippedOperator);
    }

    public boolean isMetadataDTOOfInequalLength(MetadataSearchParamsDTO metadataSearchParamsDTO){
        String strippedProperty = metadataSearchParamsDTO.getProperty().trim();
        String strippedValue = metadataSearchParamsDTO.getValue().trim();
        String strippedOperator = metadataSearchParamsDTO.getOperator().trim();

        return "".equals(strippedProperty) || "".equals(strippedValue) || "".equals(strippedOperator);
    }

    public List<MetadataSearchParams> parseMetadataDTO(MetadataSearchParamsDTO metadataSearchParamsDTO){
        String[] properties = metadataSearchParamsDTO.getProperty().trim().split("\\|");
        String[] values = metadataSearchParamsDTO.getValue().trim().split("\\|");
        String[] operators = metadataSearchParamsDTO.getOperator().trim().split("\\|");

        if ( this.isMetadataDTOOfInequalLength(metadataSearchParamsDTO) || (properties.length != values.length || properties.length != operators.length)){
            return null;
        }

        List<MetadataSearchParams> parsedSearchParams = new ArrayList<>();

        for (int i = 0 ; i < properties.length ; i++){
            MetadataSearchParams mp = new MetadataSearchParams(properties[i], values[i], operators[i]);
            parsedSearchParams.add(mp);
        }

        return parsedSearchParams;
    }

    public boolean addPrilog(String brojPrijaveZiga, String prilogType,  MultipartFile uploadedFile) throws IOException, JAXBException, XMLDBException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getZahtevForPrilogAddition(brojPrijaveZiga);
        if (zahtevZaPriznanjeZiga == null) {
            return false;
        }

        ETip_priloga tipPriloga = ETip_priloga.valueOf(prilogType);
        String fileName = brojPrijaveZiga.replace('/', '_').concat("_").concat(prilogType);

        if (ETip_priloga.PRIMERAK_ZNAKA == tipPriloga){
            fileName = fileName.concat(".jpg");
        } else {
            fileName = fileName.concat(".pdf");
        }

        System.out.println(fileName);

        boolean isOkay = writeFile(fileName, uploadedFile);
        if (!isOkay)
        {
            return false;
        }

        setPrilogDetails(zahtevZaPriznanjeZiga, fileName, tipPriloga);

        if (ETip_priloga.PRIMERAK_ZNAKA == tipPriloga){
            zahtevZaPriznanjeZiga.getZig().setIzgledPutanjaDoSlike(fileName);
            zahtevZaPriznanjeZiga.getTaksa().setTaksaZaGrafickoResenje(0);
        }

//        saveZahtev(zahtevZaPriznanjeZiga);

        return true;
    }

    public boolean saveZahtevAfterPrilogAddition(String brojPrijaveZiga) throws JAXBException, XMLDBException, IOException {
        if (!prilogUpdatingZahtevs.containsKey(brojPrijaveZiga)){
            return false;
        }

        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = prilogUpdatingZahtevs.get(brojPrijaveZiga);
        if (zahtevZaPriznanjeZiga == null){
            return false;
        }

        saveZahtev(zahtevZaPriznanjeZiga);
        prilogUpdatingZahtevs.remove(brojPrijaveZiga);

        return true;
    }

    private ZahtevZaPriznanjeZiga getZahtevForPrilogAddition(String brojPrijaveZiga){
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = null;

        prilogUpdatingZahtevsLock.lock();
        try{
            if (!prilogUpdatingZahtevs.containsKey(brojPrijaveZiga)){
                zahtevZaPriznanjeZiga = getZahtev(brojPrijaveZiga);

                if (zahtevZaPriznanjeZiga == null){
                    return null;
                }

                prilogUpdatingZahtevs.put(brojPrijaveZiga, zahtevZaPriznanjeZiga);
            } else {
                zahtevZaPriznanjeZiga = prilogUpdatingZahtevs.get(brojPrijaveZiga);
            }
        } finally {
            prilogUpdatingZahtevsLock.unlock();
        }

        return zahtevZaPriznanjeZiga;
    }

    private void setPrilogDetails(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga, String fileName, ETip_priloga prilogType){
        if (ETip_priloga.PUNOMOCJE == prilogType){
            zahtevZaPriznanjeZiga.getPrilogPunomocje().setStatusPriloga(EStatus_prilog_punomocje.PREDATO);
            zahtevZaPriznanjeZiga.getPrilogPunomocje().setPutanjaDoPriloga(fileName);
        } else {
            for (Prilog p : zahtevZaPriznanjeZiga.getPrilog()){
                if (p.getTipPriloga() == prilogType){
                    p.setStatusPriloga(EStatus_priloga.PREDATO);
                    p.setPutanjaDoPriloga(fileName);
                    break;
                }
            }
        }
    }

    public boolean writeFile(String fileName, MultipartFile uploadedFile) throws IOException {
        File file = new File(filePathForUploadedFiles + fileName);

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(uploadedFile.getBytes());
            System.out.println("Saved new file: " + fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ByteArrayInputStream getPrilog(String fileName) throws IOException {
        String fullpath = filePathForUploadedFiles.concat(fileName);

        return getFile(fullpath);
    }

    public ByteArrayInputStream getGenerated(String fileName) throws IOException {
        String fullpath = filePathForGeneratedFiles.concat(fileName);

        return getFile(fullpath);
    }

    public ByteArrayInputStream getFile(String fullpath) throws IOException {
        File file = new File(fullpath);
        if(file.exists() && !file.isDirectory()) {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } else {
            return null;
        }
    }

    public void addQr(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        String brojPrijaveZiga = zahtevZaPriznanjeZiga.getBrojPrijaveZiga();
        String qrFileName = generateQR(brojPrijaveZiga);

        if (qrFileName != null){
            Prilog qrPrilog = null;

            for (Prilog prilog : zahtevZaPriznanjeZiga.getPrilog()){
                if (prilog.getTipPriloga() == ETip_priloga.QR){
                    qrPrilog = prilog;
                    break;
                }
            }

            if (qrPrilog == null){
                qrPrilog = new Prilog();
                qrPrilog.setTipPriloga(ETip_priloga.QR);
                zahtevZaPriznanjeZiga.getPrilog().add(qrPrilog);
            }

            qrPrilog.setStatusPriloga(EStatus_priloga.PREDATO);
            qrPrilog.setPutanjaDoPriloga(qrFileName);
        }
    }

    public String generateQR(String brojPrijaveZiga){
        String httpBrojPrijaveZiga = brojPrijaveZiga.replace('/', '-');
        String fileNameBrojPrijaveZiga = brojPrijaveZiga.replace('/', '_').concat("_").concat("QR");

        String filename = null;
        try {
            filename = fileNameBrojPrijaveZiga + ".jpg";
            File file = new File(filePathForQRs + filename);
            ByteArrayOutputStream stream = QRCode
                    .from(downloadPdfHttp + httpBrojPrijaveZiga)
                    .withSize(250, 250)
                    .stream();
            FileOutputStream fos = new FileOutputStream(file);
            stream.writeTo(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return filename;
        }
    }

    public ByteArrayInputStream generateRDF(String brojPrijave) {
        try {
            String rdf = zigRepository.generateRDF(brojPrijave);
            return new ByteArrayInputStream(rdf.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateJSON(String brojPrijave) {
        try {
            String json = zigRepository.generateJSON(brojPrijave);
            return new ByteArrayInputStream(json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
