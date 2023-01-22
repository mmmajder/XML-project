package com.example.zigbackend.ktvalidators;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.zigbackend.model.*;

import java.io.File;

public class ZigTest {

    public static void main(String[] args){
        test();
    }

    public static void test() {
        try {
            System.out.println("[INFO] Zig: JAXB XML Schema validation.\n");

            JAXBContext context = JAXBContext.newInstance("com.example.zigbackend.model");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(new File("./data/zig1.xml"));

            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtevZaPriznanjeZiga);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            zahtevZaPriznanjeZiga = KTZig.createZahtevZaPriznanjeZiga();

            System.out.println("\n\n[INFO] Marshalling");
            marshaller.marshal(zahtevZaPriznanjeZiga, new File("data/test_zig.xml"));
            System.out.println("Saved to file: data/test_zig.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
