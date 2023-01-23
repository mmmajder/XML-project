package com.example.zigbackend.service;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallerZig {
    private static final String TARGET_NAMESPACE = "com.example.zigbackend.model";

    public static void marshalToFile(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("zig");
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

    public static ZahtevZaPriznanjeZiga unmarshal(XMLResource res) throws JAXBException, XMLDBException {
        JAXBContext context = JAXBContext.newInstance(TARGET_NAMESPACE);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(res.getContentAsDOM());

        return zahtevZaPriznanjeZiga;
    }
}
