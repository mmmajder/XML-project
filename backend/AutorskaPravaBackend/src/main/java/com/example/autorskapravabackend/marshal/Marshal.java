package com.example.autorskapravabackend.marshal;

import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class Marshal {

    public static OutputStream marshalPatent(ZahtevZaAutorskaPrava zahtev) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.example.autorskapravabackend.model");
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
