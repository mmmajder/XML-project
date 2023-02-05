package test;

import autoskoDelo.*;
import util.PrefixMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class TestAutorskoDelo {

    public void test() {
        try {
            System.out.println("[INFO] AutorskoDelo: JAXB XML Schema validation.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("autoskoDelo");

            // Unmarshaller - zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Učitavanje XML-a u objektni model
            ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela zahtev = (ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela) unmarshaller.unmarshal(new File("./data/example.xml"));

            // Ispis sadržaja objekta
            System.out.println("[INFO] Unmarshalled content loaded from file './data/example.xml'\n");
            System.out.println(zahtev);

            // Marshaller - zadužen za konverziju iz objekta u XML
            Marshaller marshaller = context.createMarshaller();

            // Konfiguracija marshaller-a custom prefiks maperom
            marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new PrefixMapper());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Kreira se novi imenik
            zahtev = kreirajZahtev();

            // Serijalizacija objektnog modela u XML
            System.out.println("[INFO] Marshalling into the file -> marshalling.xml");
            marshaller.marshal(zahtev, new File("marshalling.xml"));

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
        sadrzajZahteva.setPodnosilacZahteva(kreirajPodatkeOPodnosiocuZahteva());
        sadrzajZahteva.setPodaciOPunomocniku(kreirajOsnovneLicnePodatke());
        sadrzajZahteva.setAutori(kreirajAutore());
        sadrzajZahteva.setAutorskoDelo(kreirajAutorskoDelo());
        return sadrzajZahteva;
    }

    private AutorskoDelo kreirajAutorskoDelo() {
        AutorskoDelo autorskoDelo = new AutorskoDelo();
        autorskoDelo.setNaslovAutorskogDela("NASLOV");
        autorskoDelo.setFormaZapisaAutorskogDela("format");
        autorskoDelo.setNacinKoriscenjaAutorskogDela("nacin koriscenja");
        autorskoDelo.setVrstaAutorskogDela("roman");
        return autorskoDelo;
    }

    private ArrayList<TAutor> kreirajAutore() {
        Autor autor = new Autor();
        autor.setPseudonim("autorski pseudonim");
        autor.setAutor(kreirajFizickoLice());
        
        TAutor tAutor = new TAutor();
        tAutor.setAnoniman(true);
        tAutor.setPodaciOAutoru(autor);
        ArrayList<TAutor> autori = new ArrayList<>();
        autori.add(tAutor);
        return autori;
    }

    private TFizickoLice kreirajFizickoLice() {
        TFizickoLice tFizickoLice = new TFizickoLice();
        tFizickoLice.setGodinaSmrti(1989);
        tFizickoLice.setEmail("pera@gmail.com");
        tFizickoLice.setDrzavljanstvo("srpsko");
        tFizickoLice.setBrojTelefona("1241424");
        tFizickoLice.setOsnovniLicniPodaci(kreirajOsnovneLicnePodatke());
        return tFizickoLice;
    }

    private PodnosilacZahteva kreirajPodatkeOPodnosiocuZahteva() {
        PodnosilacZahteva podnosilacZahteva = new PodnosilacZahteva();
        podnosilacZahteva.setPodnosilacJeIAutor(true);
        return podnosilacZahteva;
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
        ArrayList<String> lista = new ArrayList<>();
        lista.add("papir 1");
        lista.add("papir 2");
        lista.add("papir 3");
        informacijeOZahtevu.setListaPrilogaKojiSePodnoseUzZahtev(lista);
        return informacijeOZahtevu;
    }
}
