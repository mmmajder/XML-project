package com.example.autorskapravabackend.repository;

import com.example.autorskapravabackend.db.AutorskaPravaRequestDB;
import com.example.autorskapravabackend.dto.MetadataSearchParams;
import com.example.autorskapravabackend.marshal.Marshal;
import com.example.autorskapravabackend.model.EStatus;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.rdf.AutorskaPravaFusekiDB;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutorskaPravaRepository {

    public ZahtevZaAutorskaPrava getZahtev(String brojPrijave) {
        return AutorskaPravaRequestDB.getZahtev(brojPrijave);
    }

    public List<ZahtevZaAutorskaPrava> getZahtevi() {
        return AutorskaPravaRequestDB.getZahtevi();
    }

    public int getNumberOfRequests() {
        return AutorskaPravaRequestDB.getNumberOfRequests();
    }

    public void save(ZahtevZaAutorskaPrava zahtev) {
        AutorskaPravaRequestDB.save(zahtev);
        AutorskaPravaFusekiDB.save(zahtev);
    }

    public List<ZahtevZaAutorskaPrava> getZahteviByBrojPrijave(List<String> ids) {
        List<ZahtevZaAutorskaPrava> zahtevi = new ArrayList<>();
        for (String brojPrijave : ids) {
            zahtevi.add(getZahtev(brojPrijave));
        }
        return zahtevi;
    }

    public List<ZahtevZaAutorskaPrava> getAllApplied() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.PREDATO);
    }

    public List<ZahtevZaAutorskaPrava> getAllApproved() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.PRIHVACENO);
    }

    public List<ZahtevZaAutorskaPrava> getAllCanceled() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.OBUSTAVLJENO);
    }

    public List<ZahtevZaAutorskaPrava> getAllDenied() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.ODBIJENO);
    }

    public List<ZahtevZaAutorskaPrava> getAllByStatus(EStatus status) throws JAXBException, XMLDBException {
        List<XMLResource> resources = AutorskaPravaRequestDB.getAllByStatus(status);
        return getZahteviFromResources(resources);
    }

    public List<ZahtevZaAutorskaPrava> getByText(List<String> words, boolean casesensitive) throws Exception {
        List<XMLResource> resources = AutorskaPravaRequestDB.searchResourcesForText(words, casesensitive);
        return getZahteviFromResources(resources);
    }

    private List<ZahtevZaAutorskaPrava> getZahteviFromResources(List<XMLResource> resources) throws JAXBException, XMLDBException {
        List<ZahtevZaAutorskaPrava> zahtevi = new ArrayList<>();
        for (XMLResource resource : resources){
            zahtevi.add(Marshal.unmarshal(resource));
        }
        return zahtevi;
    }

    public List<ZahtevZaAutorskaPrava> getByMetadata(MetadataSearchParams param) throws IOException {
        List<String> ids = AutorskaPravaFusekiDB.findByMetadata(param);
        return getZahteviByBrojPrijave(ids);
    }

    public List<ZahtevZaAutorskaPrava> getByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        List<String> ids = AutorskaPravaFusekiDB.findByMultipleMetadata(params);
        return getZahteviByBrojPrijave(ids);
    }

    public String generateRDF(String brojPrijave) throws Exception {
        return AutorskaPravaFusekiDB.getRdfString(brojPrijave);
    }

    public String generateJSON(String brojPrijave) throws Exception {
        return AutorskaPravaFusekiDB.getJsonString(brojPrijave);
    }
}
