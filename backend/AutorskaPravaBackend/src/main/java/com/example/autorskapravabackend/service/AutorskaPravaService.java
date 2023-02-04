package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.*;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.EStatus;
import com.example.autorskapravabackend.model.InformacijeOZahtevu;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.rdf.AutorskaPravaFusekiDB;
import com.example.autorskapravabackend.repository.AutorskaPravaRepository;
import com.example.autorskapravabackend.transformer.AutorskaPravaTransformer;
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
public class AutorskaPravaService {

    private final AutorskaPravaRepository autorskaPravaRepository = new AutorskaPravaRepository();
    private final Lock prilogUpdatingZahtevsLock = new ReentrantLock();
    private static final HashMap<String, ZahtevZaAutorskaPrava> prilogUpdatingZahtevs = new HashMap<>();

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return autorskaPravaRepository.getZahtev(brojPrijave);
    }

    public ZahtevZaAutorskaPrava createZahtevZaAutorskaPrava(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = Mapper.dtoToZahtev(dto);
        setBrojPrijave(zahtevZaAutorskaPrava);
        autorskaPravaRepository.save(zahtevZaAutorskaPrava);
        return zahtevZaAutorskaPrava;
    }

    private void setBrojPrijave(ZahtevZaAutorskaPrava zahtev) {
        InformacijeOZahtevu osnovneInformacije = new InformacijeOZahtevu();
        String brojPrijave = "A-" + LocalDate.now().getYear() + "/" + (autorskaPravaRepository.getNumberOfRequests() + 1);
        osnovneInformacije.setBrojPrijave(brojPrijave);
        osnovneInformacije.setDatumPodnosenja(new Date());
        zahtev.setInformacijeOZahtevu(osnovneInformacije);
        zahtev.setStatus(EStatus.PREDATO);
    }

    public ByteArrayInputStream generateHTML(String brojPrijave) {
        try {
            AutorskaPravaTransformer.generateZahtevHTML(getZahtev(brojPrijave), false);
            File htmlFile = new File("src/main/resources/gen/xhtml/autorskaPrava_" + brojPrijave.replace('/', '_') + ".html");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(htmlFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generatePDF(String brojPrijave) {
        try {
            AutorskaPravaTransformer.generateZahtevPDF(getZahtev(brojPrijave));
            File pdfFile = new File("src/main/resources/gen/pdf/autorskaPrava_" + brojPrijave.replace('/', '_') + ".pdf");
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateRDF(String brojPrijave) {
        try {
            String rdf = autorskaPravaRepository.generateRDF(brojPrijave);
            return new ByteArrayInputStream(rdf.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream generateJSON(String brojPrijave) {
        try {
            String json = autorskaPravaRepository.generateJSON(brojPrijave);
            return new ByteArrayInputStream(json.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ByteArrayInputStream getPrilog(String fileName) throws IOException {
        return getFile(fileName);
    }

    public ByteArrayInputStream getFile(String fullpath) throws IOException {
        File file = new File(fullpath);
        if (file.exists() && !file.isDirectory()) {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
        } else {
            return null;
        }
    }

    public List<ZahtevZaAutorskaPrava> getAllApplied() throws JAXBException, XMLDBException {
        return autorskaPravaRepository.getAllApplied();
    }

    public List<ZahtevZaAutorskaPrava> getAllApproved() throws JAXBException, XMLDBException {
        return autorskaPravaRepository.getAllApproved();
    }

    public List<ZahtevZaAutorskaPrava> getAllCanceled() throws JAXBException, XMLDBException {
        return autorskaPravaRepository.getAllCanceled();
    }

    public List<ZahtevZaAutorskaPrava> getAllDenied() throws JAXBException, XMLDBException {
        return autorskaPravaRepository.getAllDenied();
    }

    public List<MetadataSearchParams> parseMetadataDTO(MetadataSearchParamsDTO metadataSearchParamsDTO) {
        String[] properties = metadataSearchParamsDTO.getProperty().trim().split("\\|");
        String[] values = metadataSearchParamsDTO.getValue().trim().split("\\|");
        String[] operators = metadataSearchParamsDTO.getOperator().trim().split("\\|");

        if (this.isMetadataDTOOfInequalLength(metadataSearchParamsDTO) || (properties.length != values.length || properties.length != operators.length)) {
            return null;
        }

        List<MetadataSearchParams> parsedSearchParams = new ArrayList<>();

        for (int i = 0; i < properties.length; i++) {
            MetadataSearchParams mp = new MetadataSearchParams(properties[i], values[i], operators[i]);
            parsedSearchParams.add(mp);
        }

        return parsedSearchParams;
    }

    public boolean isMetadataDTOOfInequalLength(MetadataSearchParamsDTO metadataSearchParamsDTO) {
        String strippedProperty = metadataSearchParamsDTO.getProperty().trim();
        String strippedValue = metadataSearchParamsDTO.getValue().trim();
        String strippedOperator = metadataSearchParamsDTO.getOperator().trim();

        return "".equals(strippedProperty) || "".equals(strippedValue) || "".equals(strippedOperator);
    }

    public List<ZahtevZaAutorskaPrava> getByMetadata(List<MetadataSearchParams> params, boolean searchForNeobradjeni) throws IOException {
        List<ZahtevZaAutorskaPrava> zahtevi;
        if (params.size() == 1) {
            zahtevi = autorskaPravaRepository.getByMetadata(params.get(0));
        } else {
            zahtevi = autorskaPravaRepository.getByMultipleMetadata(params);
        }

        if (searchForNeobradjeni) {
            return zahtevi.stream().filter(z -> z.getStatus() == EStatus.PREDATO).collect(Collectors.toList());
        } else {
            return zahtevi.stream().filter(z -> z.getStatus() != EStatus.PREDATO).collect(Collectors.toList());
        }
    }

    private ZahtevZaAutorskaPrava getZahtevForPrilogAddition(String brojPrijave) {
        ZahtevZaAutorskaPrava ZahtevZaAutorskaPrava;

        prilogUpdatingZahtevsLock.lock();
        try {
            if (!prilogUpdatingZahtevs.containsKey(brojPrijave)) {
                ZahtevZaAutorskaPrava = getZahtev(brojPrijave);

                if (ZahtevZaAutorskaPrava == null) {
                    return null;
                }

                prilogUpdatingZahtevs.put(brojPrijave, ZahtevZaAutorskaPrava);
            } else {
                ZahtevZaAutorskaPrava = prilogUpdatingZahtevs.get(brojPrijave);
            }
        } finally {
            prilogUpdatingZahtevsLock.unlock();
        }

        return ZahtevZaAutorskaPrava;
    }

    public boolean addPrilog(String brojPrijave, String prilogType, MultipartFile uploadedFile) {
        ZahtevZaAutorskaPrava ZahtevZaAutorskaPrava = getZahtevForPrilogAddition(brojPrijave);
        if (ZahtevZaAutorskaPrava == null) {
            return false;
        }
        String fileName;
        if (prilogType.equals("OPIS")) {
            fileName = brojPrijave.replace('/', '_').concat("_OPIS.pdf");
            ZahtevZaAutorskaPrava.setPutanjaDoOpisa("src/main/resources/uploadedFiles/" + fileName);
        } else {
            fileName = brojPrijave.replace('/', '_').concat("_PRIMER.pdf");
            ZahtevZaAutorskaPrava.setPutanjaDoPrimera("src/main/resources/uploadedFiles/" + fileName);
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

        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = prilogUpdatingZahtevs.get(brojPrijaveZiga);
        if (zahtevZaAutorskaPrava == null) {
            return false;
        }

        autorskaPravaRepository.save(zahtevZaAutorskaPrava);
        prilogUpdatingZahtevs.remove(brojPrijaveZiga);

        return true;
    }

    public List<ZahtevZaAutorskaPrava> getByText(String text, boolean casesensitive, boolean searchForNeobradjeni) throws Exception {
        List<String> searchWords = Arrays.asList(text.split(" "));

        if (searchWords.size() == 0) {
            return null;
        }

        List<ZahtevZaAutorskaPrava> zahtevi = autorskaPravaRepository.getByText(searchWords, casesensitive);

        if (searchForNeobradjeni) {
            return zahtevi.stream().filter(z -> z.getStatus() == EStatus.PREDATO).collect(Collectors.toList());
        } else {
            return zahtevi.stream().filter(z -> z.getStatus() != EStatus.PREDATO).collect(Collectors.toList());
        }
    }

    public ByteArrayInputStream generateIzvestaj(IzvestajRequest izvestajRequest) throws FileNotFoundException {
        try {
            return AutorskaPravaFusekiDB.generateReport(izvestajRequest);
        } catch (Exception e) {
            throw new FileNotFoundException("Couldn't generate report");
        }
    }

    public void setObradjen(String brojPrijave, boolean odbijen) {
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = autorskaPravaRepository.getZahtev(brojPrijave);
        if(odbijen) {
            zahtevZaAutorskaPrava.setStatus(EStatus.ODBIJENO);
        } else {
            zahtevZaAutorskaPrava.setStatus(EStatus.PRIHVACENO);
        }
        autorskaPravaRepository.save(zahtevZaAutorskaPrava);
    }
}
