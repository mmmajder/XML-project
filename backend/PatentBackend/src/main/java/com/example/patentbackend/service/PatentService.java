package com.example.patentbackend.service;

import com.example.patentbackend.dto.MetadataSearchParams;
import com.example.patentbackend.dto.MetadataSearchParamsDTO;
import com.example.patentbackend.dto.NazivPrijaveDTO;
import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.mapper.Mapper;
import com.example.patentbackend.model.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.repository.PatentRepository;
import com.example.patentbackend.transformer.PatentTransformer;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Service
public class PatentService {

    private PatentRepository patentRepository = new PatentRepository();
    private final Lock prilogUpdatingZahtevsLock = new ReentrantLock();
    private static final HashMap<String, ZahtevZaPriznanjePatenta> prilogUpdatingZahtevs = new HashMap<>();

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return patentRepository.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public ZahtevZaPriznanjePatenta createZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatentaDTO zahtevZaPriznanjePatentaDTO) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = Mapper.mapToZahtevZaPriznanjePatenta(zahtevZaPriznanjePatentaDTO);
        setBrojPrijave(zahtevZaPriznanjePatenta);
        if (zahtevZaPriznanjePatentaDTO.getPutanjaDoPrilogaPodnosioca() != null)
            zahtevZaPriznanjePatenta.setPutanjaDoPrilogaPodnosioca(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave().replace('/', '_') + "_PODNOSIOCI.pdf");
        if (zahtevZaPriznanjePatentaDTO.getPutanjaDoPrimera() != null)
            zahtevZaPriznanjePatenta.setPutanjaDoPrimera(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave().replace('/', '_') + "_PRAVO_PRIJAVE.pdf");
        setDatumi(zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta());
        patentRepository.createPatentRequest(zahtevZaPriznanjePatenta);
        return zahtevZaPriznanjePatenta;
    }

    private void setDatumi(OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta) {
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(new Date());
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(new Date());
    }

    private void setBrojPrijave(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacije = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
        String brojPrijave = "P-" + LocalDate.now().getYear() + "/" + (patentRepository.getNumberOfRequests() + 1);
        osnovneInformacije.setBrojPrijave(brojPrijave);
        osnovneInformacije.setStanje("NA_CEKANJU");
        zahtevZaPriznanjePatenta.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(osnovneInformacije);
    }

    public List<ZahtevZaPriznanjePatenta> getAllPending() throws JAXBException, XMLDBException {
        return patentRepository.getAllPending();
    }

    public List<ZahtevZaPriznanjePatenta> getAllAccepted() throws JAXBException, XMLDBException {
        return patentRepository.getAllAccepted();
    }

    public List<ZahtevZaPriznanjePatenta> getAllDenied() throws JAXBException, XMLDBException {
        return patentRepository.getAllDenied();
    }

    public List<ZahtevZaPriznanjePatenta> getAllCanceled() throws JAXBException, XMLDBException {
        return patentRepository.getAllCanceled();
    }


    public ByteArrayInputStream generateHTML(String brojPrijave) {
        try {
            PatentTransformer.generateZahtevHTML(getZahtev(brojPrijave), false);
            File htmlFile = new File("src/main/resources/gen/xhtml/patent" + brojPrijave.replace('/', '_') + ".html");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(htmlFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean generatePDF(NazivPrijaveDTO brojPrijave) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = getZahtevZaPriznanjePatenta(brojPrijave.getNaziv());
        if (zahtevZaPriznanjePatenta == null) {
            return false;
        }
        PatentTransformer.generatePDFPatent(zahtevZaPriznanjePatenta);
        return true;
    }

    public ByteArrayInputStream generatePDF(String brojPrijave) {
        try {
            PatentTransformer.generateZahtevPDF(getZahtev(brojPrijave));
            File pdfFile = new File("src/main/resources/gen/pdf/patent" + brojPrijave.replace('/', '_') + ".pdf");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateRDF(String brojPrijave) {
        try {
            String rdf = patentRepository.generateRDF(brojPrijave);
            return new ByteArrayInputStream(rdf.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateJSON(String brojPrijave) {
        try {
            String json = patentRepository.generateJSON(brojPrijave);
            return new ByteArrayInputStream(json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream getPrilog(String fileName) throws IOException {
        return getFile("src/main/resources/uploadedFiles/" + fileName);
    }

    public ByteArrayInputStream getFile(String fullpath) throws IOException {
        File file = new File(fullpath);
        if (file.exists() && !file.isDirectory()) {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } else {
            return null;
        }
    }


    public ZahtevZaPriznanjePatenta getZahtev(String brojPrijave) {
        return patentRepository.getZahtev(brojPrijave);
    }

    public List<MetadataSearchParams> parseMetadataDTO(MetadataSearchParamsDTO metadataSearchParamsDTO) {
        String[] properties = metadataSearchParamsDTO.getProperty().trim().split("\\|");
        String[] values = metadataSearchParamsDTO.getValue().trim().split("\\|");
        String[] operators = metadataSearchParamsDTO.getOperator().trim().split("\\|");

        if (isMetadataDTOOfUnequalLength(metadataSearchParamsDTO) || (properties.length != values.length || properties.length != operators.length)) {
            return null;
        }

        List<MetadataSearchParams> parsedSearchParams = new ArrayList<>();

        for (int i = 0; i < properties.length; i++) {
            MetadataSearchParams mp = new MetadataSearchParams(properties[i], values[i], operators[i]);
            parsedSearchParams.add(mp);
        }

        return parsedSearchParams;
    }

    public List<ZahtevZaPriznanjePatenta> getByMetadata(List<MetadataSearchParams> params, boolean searchForNeobradjeni) throws IOException {
        List<ZahtevZaPriznanjePatenta> zahtevi;
        if (params.size() == 1) {
            zahtevi = patentRepository.getByMetadata(params.get(0));
        } else {
            zahtevi = patentRepository.getByMultipleMetadata(params);
        }

        if (searchForNeobradjeni) {
            return zahtevi.stream().filter(z -> Objects.equals(z.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getStanje(), "NA_CEKANJU")).collect(Collectors.toList());
        } else {
            return zahtevi.stream().filter(z -> !Objects.equals(z.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getStanje(), "NA_CEKANJU")).collect(Collectors.toList());
        }
    }

    public boolean isMetadataDTOOfUnequalLength(MetadataSearchParamsDTO metadataSearchParamsDTO) {
        String strippedProperty = metadataSearchParamsDTO.getProperty().trim();
        String strippedValue = metadataSearchParamsDTO.getValue().trim();
        String strippedOperator = metadataSearchParamsDTO.getOperator().trim();

        return "".equals(strippedProperty) || "".equals(strippedValue) || "".equals(strippedOperator);
    }

    public List<ZahtevZaPriznanjePatenta> getByText(String text, boolean casesensitive, boolean searchForNeobradjeni) throws Exception {
        List<String> searchWords = Arrays.asList(text.split(" "));

        if (searchWords.size() == 0) {
            return null;
        }

        List<ZahtevZaPriznanjePatenta> zahtevi = patentRepository.getByText(searchWords, casesensitive);

        if (searchForNeobradjeni) {
            return zahtevi.stream().filter(z -> Objects.equals(z.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getStanje(), "NA_CEKANJU")).collect(Collectors.toList());
        } else {
            return zahtevi.stream().filter(z -> !Objects.equals(z.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getStanje(), "NA_CEKANJU")).collect(Collectors.toList());
        }
    }

    public boolean addPrilog(String brojPrijave, String prilogType, MultipartFile uploadedFile) {
        ZahtevZaPriznanjePatenta zahtev = getZahtevForPrilogAddition(brojPrijave);
        if (zahtev == null) {
            return false;
        }
        String fileName;
        if (prilogType.equals("Podnosioc")) {
            fileName = brojPrijave.replace('/', '_').concat("_PODNOSIOCI.pdf");
            zahtev.setPutanjaDoPrilogaPodnosioca("src/main/resources/uploadedFiles/" + fileName);
        } else {
            fileName = brojPrijave.replace('/', '_').concat("_PRAVO_PRIJAVE.pdf");
            zahtev.setPutanjaDoPrimera("src/main/resources/uploadedFiles/" + fileName);
        }
        return writeFile(fileName, uploadedFile);
    }

    public boolean writeFile(String fileName, MultipartFile uploadedFile) {
        File file = new File("src/main/resources/uploadedFiles/" + fileName);

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(uploadedFile.getBytes());
            System.out.println("Saved new file: " + fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean saveZahtevAfterPrilogAddition(String brojPrijaveZiga) {
        if (!prilogUpdatingZahtevs.containsKey(brojPrijaveZiga)) {
            return false;
        }

        ZahtevZaPriznanjePatenta zahtev = prilogUpdatingZahtevs.get(brojPrijaveZiga);
        if (zahtev == null) {
            return false;
        }

        patentRepository.createPatentRequest(zahtev);
        prilogUpdatingZahtevs.remove(brojPrijaveZiga);

        return true;
    }

    private ZahtevZaPriznanjePatenta getZahtevForPrilogAddition(String brojPrijave) {
        ZahtevZaPriznanjePatenta zahtev;

        prilogUpdatingZahtevsLock.lock();
        try {
            if (!prilogUpdatingZahtevs.containsKey(brojPrijave)) {
                zahtev = getZahtev(brojPrijave);

                if (zahtev == null) {
                    return null;
                }

                prilogUpdatingZahtevs.put(brojPrijave, zahtev);
            } else {
                zahtev = prilogUpdatingZahtevs.get(brojPrijave);
            }
        } finally {
            prilogUpdatingZahtevsLock.unlock();
        }

        return zahtev;
    }
//    public List<ZahtevZaPriznanjePatenta> getAllZahtevZaPriznanjePatenta() {
//        return patentRepository.getAll();
//    }
}
