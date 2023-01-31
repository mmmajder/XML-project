package com.example.patentbackend.marshal;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.resenje.ResenjeZahteva;
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

    private static final String TARGET_NAMESPACE = "com.example.patentbackend.model";
    private static final String TARGET_NAMESPACE_RESENJE = "com.example.patentbackend.resenje";
    public static OutputStream marshalPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtevZaPriznanjePatenta, os);
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

    public static ZahtevZaPriznanjePatenta unmarshal(XMLResource res) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        RemoteXMLResource r = (RemoteXMLResource) res;
        return (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(r.getStreamContent());
    }
}
