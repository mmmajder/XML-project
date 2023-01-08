package com.example.patentbackend.marshal;

import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class Marshal {

    public static OutputStream marshalPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.example.patentbackend.model");
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtevZaPriznanjePatenta, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
