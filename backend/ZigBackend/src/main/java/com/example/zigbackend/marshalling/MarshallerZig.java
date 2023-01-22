package com.example.zigbackend.marshalling;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MarshallerZig {

    public static void marshal(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("zig");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtevZaPriznanjeZiga, new File("marshalled/" + zahtevZaPriznanjeZiga.getBrojPrijaveZiga() + ".xml"));
    }
}
