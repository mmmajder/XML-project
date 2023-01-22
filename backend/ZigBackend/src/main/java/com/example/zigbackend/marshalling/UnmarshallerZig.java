package com.example.zigbackend.marshalling;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class UnmarshallerZig {


    public static ZahtevZaPriznanjeZiga unmarshal(String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("zig");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(new File(filename));

        return zahtevZaPriznanjeZiga;
    }
}
