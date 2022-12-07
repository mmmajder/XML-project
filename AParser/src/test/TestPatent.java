package test;

import patent.*;
import util.PrefixMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class TestPatent {

    public void test() {
        try {
            System.out.println("[INFO] Patent: JAXB XML Schema validation.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("patent");

            // Unmarshaller - zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Učitavanje XML-a u objektni model
            ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela zahtev = (ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela) unmarshaller.unmarshal(new File("./data/example.xml"));

            // Ispis sadržaja objekta
            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtev);

            // Marshaller - zadužen za konverziju iz objekta u XML
            Marshaller marshaller = context.createMarshaller();

            // Konfiguracija marshaller-a custom prefiks maperom
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new PrefixMapper());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Kreira se novi imenik
            zahtev = kreirajZahtev();

            // Serijalizacija objektnog modela u XML
            System.out.println("[INFO] Marshalling customized address book:");
            marshaller.marshal(zahtev, System.out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela kreirajZahtev() {
        ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela zahtev = new ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela();
        zahtev.setSadrzajZahteva(kreirajSadrzajZahteva());
        zahtev.setInformacijeOZahtevu(kreirajInformacijeOZahtevu());
        return zahtev;
    }

    private SadrzajZahteva kreirajSadrzajZahteva() {
        SadrzajZahteva sadrzajZahteva = new SadrzajZahteva();
        sadrzajZahteva.setFormaZapisaAutorskogDela("roman");
        sadrzajZahteva.setPodnosilacZahteva(kreirajPodatkeOPodnosiocuZahteva());
        sadrzajZahteva.setAutorskoDeloStvorenoURadnomOdnosu(false);
        sadrzajZahteva.setNacinKoriscenjaAutorskogDela("nacin koriscenja");
        sadrzajZahteva.setNaslovAutorskogDela("Mnogo dobro delo");
        sadrzajZahteva.setNaslovIAutorZasnivanogDela("Naslov dela na kojem se zasniva");
        sadrzajZahteva.setPodaciOAutoru("Podaci o autoru");
        sadrzajZahteva.setPseudonimAutora("Perica");
        sadrzajZahteva.setPodaciOPunomocniku("Pera");
        return sadrzajZahteva;
    }

    private TLice kreirajPodatkeOPodnosiocuZahteva() {
        TFizickoLice tlice = new TFizickoLice();
        tlice.setEmail("pera@gmail.com");
        tlice.setBrojTelefona("0612345353");
        tlice.setDrzavljanstvo("srpsko");
        tlice.setOsnovniLicniPodaci(kreirajOsnovneLicnePodatke());
        return tlice;
    }

    private OsnovniLicniPodaci kreirajOsnovneLicnePodatke() {
        OsnovniLicniPodaci osnovniLicniPodaci = new OsnovniLicniPodaci();
        osnovniLicniPodaci.setIme("Pera");
        osnovniLicniPodaci.setPrezime("Peric");
        osnovniLicniPodaci.setAdresa(kreirajAdresu());
        return osnovniLicniPodaci;
    }

    private Adresa kreirajAdresu() {
        Adresa adresaLica = new Adresa();
        adresaLica.setBrojUUlici(2);
        adresaLica.setDrzava("Crna Gora");
        adresaLica.setMesto("Niksic");
        adresaLica.setPostanskiBroj(432423);
        adresaLica.setUlica("Bla bla");
        return adresaLica;
    }

    private InformacijeOZahtevu kreirajInformacijeOZahtevu() {
        InformacijeOZahtevu informacijeOZahtevu = new InformacijeOZahtevu();
        informacijeOZahtevu.setBrojPrijave("A-214");
        informacijeOZahtevu.setDatumPodnosenja(new Date());
        informacijeOZahtevu.setListaPrilogaKojiSePodnoseUzZahtev(new ArrayList<>());
        return informacijeOZahtevu;
    }
}
