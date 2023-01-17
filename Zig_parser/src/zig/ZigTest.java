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
        zahtev.setZatrazenoPravoPrvenstvaIOsnov(EZatrazeno_pravo_prvenstva_i_osnov.KONVENCIJSKO);
        zahtev.setTaksa(createTaksa());
        zahtev.setPrilog(createPrilog());
        zahtev.setPrilogPunomocje(createPrilogPunomocje());
        zahtev.setBrojPrijaveZiga("Z-12345/22");
        zahtev.setDatumPodnosenja(Date.valueOf(LocalDate.parse("2022-05-06")));
        zahtev.setStatus(EStatus.PREDATO);

        return zahtev;
    }

    public static Lice createPodnosilacPrijave(){
        FizickoLice lice = new FizickoLice();
        lice.setIme("Jovan");
        lice.setPrezime("Jovic");
        lice.setAdresa(new Adresa("Jovanova ulica", 3, 33000, "Jovangrad", "Srbija"));
        lice.setKontakt(new Kontakt("06721389832", "jovanovemail@jovan.com", "233452153"));

        return lice;
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
        zig.setTipZiga(ETip_ziga.INDIVIDUALNI_ZIG);
        zig.setOpisIzgledaZiga(EOpis_izgleda_ziga.KOMBINOVANI_ZNAK);
        zig.setIzgledPutanjaDoSlike("putanja");
        
        List<EBoja> boje = new ArrayList<>();
        boje.add(EBoja.PLAVA);
        boje.add(EBoja.ZUTA);
        boje.add(EBoja.ROZE);
        zig.setBoja(boje);
        
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
        p1.setTipPriloga(ETip_priloga.PRIMERAK_ZNAKA);
        p1.setStatusPriloga(EStatus_priloga.PREDATO);
        p1.setPutanjaDoPriloga("putanja1");
        prilozi.add(p1);

        Prilog p2 = new Prilog();
        p2.setTipPriloga(ETip_priloga.SPISAK_ROBE_I_USLUGA);
        p2.setStatusPriloga(EStatus_priloga.PREDATO);
        p2.setPutanjaDoPriloga("putanja2");
        prilozi.add(p2);

        Prilog p3 = new Prilog();
        p3.setTipPriloga(ETip_priloga.OPSTI_AKT_O_ZIGU);
        p3.setStatusPriloga(EStatus_priloga.NIJE_POTREBNO);
        prilozi.add(p3);

        Prilog p4 = new Prilog();
        p4.setTipPriloga(ETip_priloga.DOKAZ_O_PRAVU_PRVENSTVA);
        p4.setStatusPriloga(EStatus_priloga.NIJE_PREDATO);
        prilozi.add(p4);

        Prilog p5 = new Prilog();
        p5.setTipPriloga(ETip_priloga.DOKAZ_O_UPLATI_TAKSE);
        p5.setStatusPriloga(EStatus_priloga.NIJE_PREDATO);
        prilozi.add(p5);

        return prilozi;
    }

    public static PrilogPunomocje createPrilogPunomocje(){
        PrilogPunomocje pp = new PrilogPunomocje();
        pp.setStatusPriloga(EStatus_prilog_punomocje.PREDATO);
        pp.setPutanjaDoPriloga("putanja");

        return pp;
    }
}
