package com.example.zigbackend.repository;

import com.example.zigbackend.db.ZigDB;
import com.example.zigbackend.dto.MetadataSearchParams;
import com.example.zigbackend.fuseki.ZigFusekiDB;
import com.example.zigbackend.model.EStatus;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.service.MarshallerZig;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZigRepository {

    public ZahtevZaPriznanjeZiga save(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws JAXBException, IOException, XMLDBException {
        ZigDB.save(zahtevZaPriznanjeZiga);
        ZigFusekiDB.save(zahtevZaPriznanjeZiga);

        return zahtevZaPriznanjeZiga;
    }

    public int getNumberOfZahtevi() {
        return ZigDB.getNumberOfZahtevi();
    }

    public static int getNumberOfZahteviStatic() {
        return ZigDB.getNumberOfZahtevi();
    }

    public ZahtevZaPriznanjeZiga getZahtev(String brojPrijave) {
        return ZigDB.getZahtev(brojPrijave);
    }

    public List<ZahtevZaPriznanjeZiga> getZahteviByBrojPrijave(List<String> ids){
        List<ZahtevZaPriznanjeZiga> zahtevi = new ArrayList<>();

        for (String brojPrijave : ids){
            ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = getZahtev(brojPrijave);
            zahtevi.add(zahtevZaPriznanjeZiga);
        }

        return zahtevi;
    }

    public List<XMLResource> getAllForYear(String year){
        Character y1 = year.charAt(2);
        Character y2 = year.charAt(3);
        String yy = y1.toString().concat(y2.toString());

        return ZigDB.getAllByYear(yy);
    }

    public List<ZahtevZaPriznanjeZiga> getAllApplied() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.PREDATO);
    }

    public List<ZahtevZaPriznanjeZiga> getAllApproved() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.PRIHVACENO);
    }

    public List<ZahtevZaPriznanjeZiga> getAllCanceled() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.OBUSTAVLJENO);
    }

    public List<ZahtevZaPriznanjeZiga> getAllDenied() throws JAXBException, XMLDBException {
        return getAllByStatus(EStatus.ODBIJENO);
    }

    private List<ZahtevZaPriznanjeZiga> getAllByStatus(EStatus status) throws JAXBException, XMLDBException {
        List<XMLResource> resources = ZigDB.getAllByStatus(status);
        List<ZahtevZaPriznanjeZiga> zahtevi = getZahteviFromResources(resources);

        return zahtevi;
    }

    // search vvv
    public List<ZahtevZaPriznanjeZiga> getByText(List<String> words, boolean casesensitive) throws Exception {
        List<XMLResource> resources = ZigDB.searchResourcesForText(words, casesensitive);
        System.out.println(resources);
        List<ZahtevZaPriznanjeZiga> zahtevi = getZahteviFromResources(resources);

        return zahtevi;
    }

    private List<ZahtevZaPriznanjeZiga> getZahteviFromResources(List<XMLResource> resources) throws JAXBException, XMLDBException {
        List<ZahtevZaPriznanjeZiga> zahtevi = new ArrayList<>();

        for (XMLResource resource : resources){
            System.out.println("1");
            ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = MarshallerZig.unmarshal(resource);
            zahtevi.add(zahtevZaPriznanjeZiga);
        }

        return zahtevi;
    }

    public List<ZahtevZaPriznanjeZiga> getByMetadata(MetadataSearchParams param) throws IOException {
        List<String> ids = ZigFusekiDB.findByMetadata(param);
        List<ZahtevZaPriznanjeZiga> zahtevi = getZahteviByBrojPrijave(ids);

        return zahtevi;
    }

    public List<ZahtevZaPriznanjeZiga> getByMultipleMetadata(List<MetadataSearchParams> params) throws IOException {
        List<String> ids = ZigFusekiDB.findByMultipleMetadata(params);
        List<ZahtevZaPriznanjeZiga> zahtevi = getZahteviByBrojPrijave(ids);

        return zahtevi;
    }
    // search ^^^

}
