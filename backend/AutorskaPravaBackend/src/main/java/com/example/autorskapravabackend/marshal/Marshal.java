package com.example.autorskapravabackend.marshal;

import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.resenje.ResenjeZahteva;
import org.exist.xmldb.RemoteXMLResource;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class Marshal {

    private static final String TARGET_NAMESPACE = "com.example.autorskapravabackend.model";
    private static final String TARGET_NAMESPACE_RESENJE = "com.example.autorskapravabackend.resenje";

    public static OutputStream marshal(ZahtevZaAutorskaPrava zahtev) {
        try {
            JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static OutputStream marshal(ResenjeZahteva zahtev) throws SAXException {
        try {
            JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE_RESENJE);
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static ZahtevZaAutorskaPrava unmarshal(XMLResource res) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        RemoteXMLResource r = (RemoteXMLResource) res;
        return (ZahtevZaAutorskaPrava) unmarshaller.unmarshal(r.getStreamContent());
    }
}
