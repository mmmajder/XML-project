package fuseki;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.jena.rdf.model.Model;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import db.ZigDB;
import transformer.ZigTransformer;
import zig.Adresa;
import zig.EBoja;
import zig.EOpis_izgleda_ziga;
import zig.EStatus;
import zig.EStatus_prilog_punomocje;
import zig.EStatus_priloga;
import zig.ETip_priloga;
import zig.ETip_ziga;
import zig.EZatrazeno_pravo_prvenstva_i_osnov;
import zig.FizickoLice;
import zig.Klasa;
import zig.Kontakt;
import zig.Lice;
import zig.PravnoLice;
import zig.Prilog;
import zig.PrilogPunomocje;
import zig.Taksa;
import zig.ZahtevZaPriznanjeZiga;
import zig.Zig;

public class ZigFusekiTest {
	public static void main(String[] args) throws IOException, JAXBException, XMLDBException{
        test();
    }

    public static void test() throws IOException, JAXBException, XMLDBException {
//    	ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = createZahtevZaPriznanjeZiga();
//    	
//    	// pdf and html
//    	ZigTransformer.generateHTMLZig(zahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan HTML");
//    	ZigTransformer.generatePDFZig(zahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan PDF"); 
  	
//    	// cuvanje u bazu
//    	ZigDB.save(zahtevZaPriznanjeZiga);
//    	
//    	// fuseki
//    	Model metadataModel = ZigMetadataExtractor.extract(zahtevZaPriznanjeZiga);
//    	
//    	printModelAsRdfXml(metadataModel);
//    	
//    	FusekiWriter.saveRdfGraphToDatabase(metadataModel);
//    	FusekiReader.readAllDataFromDatabase();
////    	
////    	// ucitavanje iz baze
//    	ZahtevZaPriznanjeZiga ucitanZahtevZaPriznanjeZiga = ZigDB.getZahtev(zahtevZaPriznanjeZiga.getBrojPrijaveZiga());
//
//    	System.out.println("\nIz baze je ucitan zahtev sa brojem prijave: ");
//    	System.out.println(ucitanZahtevZaPriznanjeZiga.getBrojPrijaveZiga());
////    	
////    	 pdf and html
//    	ZigTransformer.generateHTMLZig(ucitanZahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan HTML");
//    	ZigTransformer.generatePDFZig(ucitanZahtevZaPriznanjeZiga);
//    	System.out.println("izgenerisan PDF");
//    	
//    	// metadata search
//    	MetadataSearchParams params0 = new MetadataSearchParams("tip_ziga", "INDIVIDUALNI_ZIG", "");
//    	FusekiReader.findByMetadata(params0);
////    	 combined metadata search
//    	MetadataSearchParams params1 = new MetadataSearchParams("tip_ziga", "INDIVIDUALNI_ZIG", "&&");
//    	MetadataSearchParams params2 = new MetadataSearchParams("predstavnik_email", "Predstavnik2@jovan.com", "!");
//    	MetadataSearchParams params3 = new MetadataSearchParams("predstavnik_email", "Predstavnik2@jovan.com", "OR");
//    	List<MetadataSearchParams> params = new ArrayList<>();
//    	params.add(params1);
//    	params.add(params2);
//    	params.add(params3);
//    	FusekiReader.findByCombinedMetadata(params);
    	
    	//db search by text
    	try {
			List<XMLResource> resources = ZigDB.searchResources("jovangrad", false);
			System.out.println(resources);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String[] words_ = {"jovangrad", "33000"};
    	List<String> words = Arrays.asList(words_);
    	try {
			List<XMLResource> resources = ZigDB.searchResources(words, false);
			System.out.println(resources);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private static void printModelAsRdfXml(Model model) {
    	System.out.println("[INFO] Rendering model as RDF/XML...");
    	model.write(System.out, SparqlUtil.RDF_XML);
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
        zahtev.setBrojPrijaveZiga("Z-12398/22");
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
        zig.setIzgledPutanjaDoSlike("test_logo.jpg");
        
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
        
        Klasa k2 = new Klasa();
        k2.setIdKlase("2");
        k2.setPunNazivKlase("Boje lakovi");
        klase.add(k2);

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
