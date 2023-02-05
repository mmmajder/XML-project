package com.example.patentbackend.repository;

import com.example.patentbackend.db.PatentRequestDB;
import com.example.patentbackend.dto.MetadataSearchParams;
import com.example.patentbackend.marshal.Marshal;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.rdf.PatentFusekiDB;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatentRepository {
    public void createPatentRequest(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        PatentRequestDB.save(zahtevZaPriznanjePatenta);
        PatentFusekiDB.save(zahtevZaPriznanjePatenta);
    }

    public int getNumberOfRequests() {
        return PatentRequestDB.getNumberOfRequests();
    }

    public ZahtevZaPriznanjePatenta getZahtev(String brojPrijave) {
        return PatentRequestDB.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public List<ZahtevZaPriznanjePatenta> getZahtevi() {
        return PatentRequestDB.getZahtevi();
    }

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return PatentRequestDB.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public List<ZahtevZaPriznanjePatenta> getZahteviByBrojPrijave(List<String> ids) {
        List<ZahtevZaPriznanjePatenta> zahtevi = new ArrayList<>();
        for (String brojPrijave : ids) {
            zahtevi.add(getZahtev(brojPrijave));
        }
        return zahtevi;
    }

    public List<ZahtevZaPriznanjePatenta> getAllPending() throws JAXBException, XMLDBException {
        return getAllByStatus("NA_CEKANJU");
    }

    public List<ZahtevZaPriznanjePatenta> getAllAccepted() throws JAXBException, XMLDBException {
        return getAllByStatus("USPESNO");
    }

    public List<ZahtevZaPriznanjePatenta> getAllDenied() throws JAXBException, XMLDBException {
        return getAllByStatus("ODBIJENO");
    }

    public List<ZahtevZaPriznanjePatenta> getAllCanceled() throws JAXBException, XMLDBException {
        return getAllByStatus("OBUSTAVLJENO");
    }

    public List<ZahtevZaPriznanjePatenta> getAllByStatus(String status) throws JAXBException, XMLDBException {
        List<XMLResource> resources = PatentRequestDB.getAllByStatus(status);

        return getZahteviFromResources(resources);
    }

    public List<ZahtevZaPriznanjePatenta> getByText(List<String> words, boolean casesensitive) throws Exception {
//        List<XMLResource> resources = PatentRequestDB.getAllByAtrr(words);
        List<XMLResource> resources = PatentRequestDB.searchResourcesForText(words, casesensitive);
        return getZahteviFromResources(resources);
    }

    private List<ZahtevZaPriznanjePatenta> getZahteviFromResources(List<XMLResource> resources) throws JAXBException, XMLDBException {
        List<ZahtevZaPriznanjePatenta> zahtevi = new ArrayList<>();
        for (XMLResource resource : resources) {
            ZahtevZaPriznanjePatenta newZahtev = Marshal.unmarshal(resource);
            boolean shouldAdd = true;
            for (ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta : zahtevi) {
                if (zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave().equals(newZahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave())) {
                    shouldAdd = false;
                    break;
                }
            }
            if (shouldAdd)
                zahtevi.add(newZahtev);
        }
        return zahtevi;
    }

    public List<ZahtevZaPriznanjePatenta> getByMetadata(MetadataSearchParams param) throws IOException {
        List<String> ids = PatentFusekiDB.findByMetadata(param);
        return getZahteviByBrojPrijave(ids);
    }

    public List<ZahtevZaPriznanjePatenta> getByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        List<String> ids = PatentFusekiDB.findByMultipleMetadata(params);
        return getZahteviByBrojPrijave(ids);
    }

    public String generateRDF(String brojPrijave) throws Exception {
        return PatentFusekiDB.getRdfString(brojPrijave);
    }

    public String generateJSON(String brojPrijave) throws Exception {
        return PatentFusekiDB.getJsonString(brojPrijave);
    }

    public void save(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        createPatentRequest(zahtevZaPriznanjePatenta);
    }
}
