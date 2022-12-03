package test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import patent.ZahtevZaPriznanjePatenta;
import util.NSPrefixMapper;

public class PatentTest {
	
	public void test() {
		try {
			System.out.println("[INFO] Patent: JAXB XML Schema validation.\n");
			
			// Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
			JAXBContext context = JAXBContext.newInstance("patent");
			
			// Unmarshaller - zadužen za konverziju iz XML-a u objektni model
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Učitavanje XML-a u objektni model
			ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(new File("./data/Primer_P.xml"));

			// Ispis sadržaja objekta
			System.out.println("[INFO] Unmarshalled content:");
			System.out.println(zahtevZaPriznanjePatenta);
			
			// Marshaller - zadužen za konverziju iz objekta u XML
			Marshaller marshaller = context.createMarshaller();

			// Konfiguracija marshaller-a custom prefiks maperom
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
//			// Kreira se novi imenik
//			zahtevZaPriznanjePatenta = kreirajZahtevZaPriznanjePatenta();
//
//			// Serijalizacija objektnog modela u XML
//			System.out.println("[INFO] Marshalling customized address book:");
//			marshaller.marshal(zahtevZaPriznanjePatenta, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private ZahtevZaPriznanjePatenta kreirajZahtevZaPriznanjePatenta() {
//		ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = new ZahtevZaPriznanjePatenta();
//		zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta()
//		
//	}
	public static void main(String[] args) {
		PatentTest test = new PatentTest();
		test.test();
	}
	

}
