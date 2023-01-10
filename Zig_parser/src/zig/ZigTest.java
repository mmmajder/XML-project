package zig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import util.NSPrefixMapper;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ZigTest {

    public static void main(String[] args){
        test();
    }

    public static void test() {
        try {
            System.out.println("[INFO] Zig: JAXB XML Schema validation.\n");

            JAXBContext context = JAXBContext.newInstance("zig");

            Unmarshaller unmarshaller = context.createUnmarshaller();

            ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = (ZahtevZaPriznanjeZiga) unmarshaller.unmarshal(new File("./data/zig1.xml"));

            System.out.println("[INFO] Unmarshalled content:");
            System.out.println(zahtevZaPriznanjeZiga);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            zahtevZaPriznanjeZiga = createZahtevZaPriznanjeZiga();

            System.out.println("\n\n[INFO] Marshalling");
            marshaller.marshal(zahtevZaPriznanjeZiga, new File("data/test_zig.xml"));
            System.out.println("Saved to file: data/test_zig.xml");

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ZahtevZaPriznanjeZiga createZahtevZaPriznanjeZiga(){
        ZahtevZaPriznanjeZiga zahtev = new ZahtevZaPriznanjeZiga();
        zahtev.setPodnosilacPrijave(createPodnosilacPrijave());
        zahtev.setPunomocnik(createPunomocnik());
        zahtev.setPredstavnik(createPredstavnik());
        zahtev.setZig(createZig());
        zahtev.setKlasa(createKlasa());
        zahtev.setZatrazenoPravoPrvenstvaIOsnov("Ja sam prvi pronalazac ovog magicnog praska.");
        zahtev.setTaksa(createTaksa());
        zahtev.setPrilog(createPrilog());
        zahtev.setPrilogPunomocje(createPrilogPunomocje());
        zahtev.setBrojPrijaveZiga("Z-12345/22");
        zahtev.setDatumPodnosenja(Date.valueOf(LocalDate.parse("2022-05-06")));

        return zahtev;
    }

    public static List<Lice> createPodnosilacPrijave(){
        List<Lice> podnosilacPrijave = new ArrayList<Lice>();
        FizickoLice lice = new FizickoLice();
        lice.setIme("Jovan");
        lice.setPrezime("Jovic");
        lice.setAdresa(new Adresa("Jovanova ulica", 3, 33000, "Jovangrad", "Srbija"));
        lice.setKontakt(new Kontakt("06721389832", "jovanovemail@jovan.com", "233452153"));
        podnosilacPrijave.add(lice);

        return podnosilacPrijave;
    }

    public static Lice createPunomocnik(){
        PravnoLice lice = new PravnoLice();
        lice.setPoslovnoIme("JovanovPunomocnik Company");
        lice.setAdresa(new Adresa("Company ulica", 56, 33000, "Jovangrad", "Srbija"));
        lice.setKontakt(new Kontakt("063453159", "JovanovPunomocnik@jovan.com", "8416816854"));

        return lice;
    }

    public static Lice createPredstavnik(){
        PravnoLice lice = new PravnoLice();
        lice.setPoslovnoIme("PredstavnikJovanov Company");
        lice.setAdresa(new Adresa("Predstavnik ulica", 78, 33000, "Jovangrad", "Srbija"));
        lice.setKontakt(new Kontakt("06125552255", "Predstavnik@jovan.com", "77878788778"));

        return lice;
    }

    public static Zig createZig(){
        Zig zig = new Zig();
        zig.setTipZiga("INDIVIDUALNI_ZIG");
        zig.setOpisIzgledaZiga("KOMBINOVANI_ZNAK");
        zig.setIzgledPutanjaDoSlike("putanja");
        zig.setTransliteracijaZnaka("Oh yes!");
        zig.setPrevodZnaka("Oh da!");
        zig.setOpisZnaka("Znak predstavlja zadovoljnu musteriju koja pokazuje palcem ka gore i smeje se. Oko musterijine glave je ispisan uzvik Oh yes!");

        return zig;
    }

    public static List<Klasa> createKlasa(){
        List<Klasa> klase = new ArrayList<Klasa>();
        Klasa k = new Klasa();
        k.setIdKlase("1");
        k.setPunNazivKlase("Hemijski proizvodi");
        klase.add(k);

        return klase;
    }

    public static Taksa createTaksa(){
        Taksa t = new Taksa();
        t.setOsnovnaTaksa(550);
        t.setTaksaZaSveKlase(300);
        t.setTaksaZaGrafickoResenje(420);

        return t;
    }

    public static List<Prilog> createPrilog(){
        List<Prilog> prilozi = new ArrayList<Prilog>();

        Prilog p1 = new Prilog();
        p1.setTipPriloga("PRIMERAK_ZNAKA");
        p1.setStatusPriloga("PREDAT");
        p1.setPutanjaDoPriloga("putanja1");
        prilozi.add(p1);

        Prilog p2 = new Prilog();
        p2.setTipPriloga("SPISAK_ROBE_I_USLUGA");
        p2.setStatusPriloga("PREDAT");
        p2.setPutanjaDoPriloga("putanja2");
        prilozi.add(p2);

        Prilog p3 = new Prilog();
        p3.setTipPriloga("OPSTI_AKT_O_ZIGU");
        p3.setStatusPriloga("PREDAT");
        p3.setPutanjaDoPriloga("putanja3");
        prilozi.add(p3);

        Prilog p4 = new Prilog();
        p4.setTipPriloga("DOKAZ_O_PRAVU_PRVENSTVA");
        p4.setStatusPriloga("PREDAT");
        p4.setPutanjaDoPriloga("putanja4");
        prilozi.add(p4);

        Prilog p5 = new Prilog();
        p5.setTipPriloga("DOKAZ_O_UPLATI_TAKSE");
        p5.setStatusPriloga("PREDAT");
        p5.setPutanjaDoPriloga("putanja5");
        prilozi.add(p5);

        return prilozi;
    }

    public static PrilogPunomocje createPrilogPunomocje(){
        PrilogPunomocje pp = new PrilogPunomocje();
        pp.setPunomocjePredatoRanije(false);
        pp.setPunomocjeCeBitiNaknadnoDostavljeno(false);
        pp.setStatusPriloga("PREDAT");
        pp.setPutanjaDoPriloga("putanja");

        return pp;
    }
}
