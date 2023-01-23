package com.example.zigbackend.service;

import com.example.zigbackend.dto.MetadataSearchParams;
import com.example.zigbackend.dto.MetadataSearchParamsDTO;
import com.example.zigbackend.dto.ZahtevZaPriznanjeZigaDTO;
import com.example.zigbackend.mapper.ZigMapper;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.repository.ZigRepository;
import com.example.zigbackend.transformer.ZigTransformer;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ZigService {
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
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = ZigMapper.createZahtev(zahtevZaPriznanjeZigaDTO);
        zahtevZaPriznanjeZiga.setBrojPrijaveZiga(getNewBrojPrijave());
        saveZahtev(zahtevZaPriznanjeZiga);

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

    public List<ZahtevZaPriznanjeZiga> getByText(String text, boolean casesensitive) throws Exception {
        List<String> searchWords = Arrays.asList(text.split(" "));

        if (searchWords.size() == 0){
            return null;
        }

        return zigRepository.getByText(searchWords, casesensitive);
    }

    public List<ZahtevZaPriznanjeZiga> getByMetadata(MetadataSearchParamsDTO metadataSearchParamsDTO) throws IOException {
        List<MetadataSearchParams> params = metadataSearchParamsDTO.getParams();

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
}
