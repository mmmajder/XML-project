package com.example.zigbackend.service;

import com.example.zigbackend.model.ObjectFactory;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.resenje.ResenjeZahteva;
import org.exist.xmldb.RemoteXMLResource;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.*;
import javax.xml.transform.Source;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallerZig {
    private static final String TARGET_NAMESPACE = "com.example.zigbackend.model"; // koriscenje namespace-a, a ne .class resilo problem i u transformatoru, veliki problem resen postavljanjem magicne linije u configurations VM options
    private static final String TARGET_NAMESPACE_RESENJE = "com.example.zigbackend.resenje";

    public static void marshalToFile(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtevZaPriznanjeZiga, new File("marshalled/" + zahtevZaPriznanjeZiga.getBrojPrijaveZiga() + ".xml"));
    }

    public static OutputStream marshal(ZahtevZaPriznanjeZiga zahtev) throws JAXBException, XMLDBException {
        OutputStream marshaledZahtev = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtev, marshaledZahtev);

        return marshaledZahtev;
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

    public static ZahtevZaPriznanjeZiga unmarshal(XMLResource res) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        System.out.println("2");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        System.out.println("3");
        System.out.println(res);
        RemoteXMLResource r = (RemoteXMLResource) res;
//        JAXBElement<ZahtevZaPriznanjeZiga> jaxbElement = unmarshaller.unmarshal((Source) r.getStreamContent(), ZahtevZaPriznanjeZiga.class);
//        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = jaxbElement.getValue();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(r.getStreamContent());
//        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) JAXBIntrospector.getValue(unmarshaller.unmarshal(r.getStreamContent()));
        System.out.println(zahtevZaPriznanjeZiga);

        return zahtevZaPriznanjeZiga;
    }
}
