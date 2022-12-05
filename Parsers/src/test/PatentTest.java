package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.jndi.cosnaming.IiopUrl.Address;

import patent.Adresa;
import patent.Dostavljanje;
import patent.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import patent.OsnovniPodaciOLicu;
import patent.PodaciOPodnosiocuPrijave;
import patent.PodaciOPrijavi;
import patent.PodaciOPunomocniku;
import patent.SadrzajZahtevaZaPriznanjePatenta;
import patent.TFizickoLice;
import patent.TPravnoLice;
import patent.TRanijihPrijavaZaPriznanjePrvenstva;
import patent.ZahtevZaPriznanjePatenta;
import patent.ZahtevZaPriznanjePrvenstvaIzRanijihPrijava;
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
			ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(new File("./data/P3.xml"));

			// Ispis sadržaja objekta
			System.out.println("[INFO] Unmarshalled content:");
			System.out.println(zahtevZaPriznanjePatenta);
			
			// Marshaller - zadužen za konverziju iz objekta u XML
			Marshaller marshaller = context.createMarshaller();

			// Konfiguracija marshaller-a custom prefiks maperom
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Kreira se novi imenik
			zahtevZaPriznanjePatenta = kreirajZahtevZaPriznanjePatenta();

			// Serijalizacija objektnog modela u XML
			System.out.println("[INFO] Marshalling customized address book:");
			marshaller.marshal(zahtevZaPriznanjePatenta, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ZahtevZaPriznanjePatenta kreirajZahtevZaPriznanjePatenta() {
		ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = new ZahtevZaPriznanjePatenta();
		zahtevZaPriznanjePatenta.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(kreirajOsnovneInformacije());
		zahtevZaPriznanjePatenta.setSadrzajZahtevaZaPriznanjePatenta(kreirajSadrzajZahtevaZaPrizanjePatenta());
		return zahtevZaPriznanjePatenta;
	}
	
	private SadrzajZahtevaZaPriznanjePatenta kreirajSadrzajZahtevaZaPrizanjePatenta() {
		SadrzajZahtevaZaPriznanjePatenta sadrzajZahtevaZaPriznanjePatenta = new SadrzajZahtevaZaPriznanjePatenta();
		sadrzajZahtevaZaPriznanjePatenta.setPodaciOPodnosiocuPrijave(kreirajPodatkeOPodnosiocuPrijave());
		sadrzajZahtevaZaPriznanjePatenta.setDostavljanje(kreirajDostavljanje());
		sadrzajZahtevaZaPriznanjePatenta.setNazivPronalaska(kreirajNazivPronalaska());
		sadrzajZahtevaZaPriznanjePatenta.setPodaciOPrijavi(kreirajPodatkeOPrijavi());
		sadrzajZahtevaZaPriznanjePatenta.setPodaciOPronalazacu(kreirajPotatkeOPronalazacu());
		sadrzajZahtevaZaPriznanjePatenta.setPodaciOPunomocniku(kreirajPodatkeOPunomocniku());
		sadrzajZahtevaZaPriznanjePatenta.setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(kreirajZahtevZaPriznanjePrvenstva());
		return sadrzajZahtevaZaPriznanjePatenta;
	}

	private ZahtevZaPriznanjePrvenstvaIzRanijihPrijava kreirajZahtevZaPriznanjePrvenstva() {
		TRanijihPrijavaZaPriznanjePrvenstva ranijaPrijava = new TRanijihPrijavaZaPriznanjePrvenstva();
		ranijaPrijava.setBrojRanijePrijave("P1231231");
		ranijaPrijava.setDatumaPodnosenjaPrijave(new Date(2000,2,2));
		ranijaPrijava.setDvoslovnaOznaka("NS");
		ArrayList<TRanijihPrijavaZaPriznanjePrvenstva> listaRanijihPrijava = new ArrayList<>();
		listaRanijihPrijava.add(ranijaPrijava);
		ZahtevZaPriznanjePrvenstvaIzRanijihPrijava zahtevZaPriznanjePrvenstvaIzRanijihPrijava = new ZahtevZaPriznanjePrvenstvaIzRanijihPrijava();
		zahtevZaPriznanjePrvenstvaIzRanijihPrijava.setRanijePrijave(listaRanijihPrijava);
		return zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	}

	private PodaciOPunomocniku kreirajPodatkeOPunomocniku() {
		TPravnoLice punomocnik = new TPravnoLice();
		OsnovniPodaciOLicu osnovniPodaciOPunomocniku = new OsnovniPodaciOLicu();
		Adresa adresa = new Adresa();
		adresa.setBrojUUlici(3);
		adresa.setDrzava("Srbija");
		adresa.setMesto("Novi Sad");
		adresa.setPostanskiBroj(21000);
		adresa.setUlica("Bulevar Evrope");
		osnovniPodaciOPunomocniku.setAdresaLica(adresa);
		osnovniPodaciOPunomocniku.setBrojFaksa("321321");
		osnovniPodaciOPunomocniku.setBrojTelefona("+381 12315466");
		osnovniPodaciOPunomocniku.setePosta("Mejlpunomocnika@gmial.com");
		punomocnik.setOsnovniPodaciOLicu(osnovniPodaciOPunomocniku);
		punomocnik.setPoslovnoIme("Sverc komerc");
		PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
		podaciOPunomocniku.setPunomocnik(punomocnik);
		podaciOPunomocniku.setVrstaPunomocnika("PUNOMOCNIK_ZA_PRIJEM_PISMENA");
		podaciOPunomocniku.setZajednickiPredstavnik(false);
		return podaciOPunomocniku;
	}

	private TFizickoLice kreirajPotatkeOPronalazacu() {
		TFizickoLice pronalazac = new TFizickoLice();
		pronalazac.setDrzavljanstvo("Srpsko");
		pronalazac.setIme("Pera");
		pronalazac.setPrezime("Peric");
		OsnovniPodaciOLicu osnovniPodaciOLicu = new OsnovniPodaciOLicu();
		Adresa adresaLica= new Adresa();
		adresaLica.setBrojUUlici(2);
		adresaLica.setDrzava("Crna Gora");
		adresaLica.setMesto("Niksic");
		adresaLica.setPostanskiBroj(432423);
		adresaLica.setUlica("Bla bla");
		osnovniPodaciOLicu.setAdresaLica(adresaLica);
		osnovniPodaciOLicu.setBrojFaksa("4234234");
		osnovniPodaciOLicu.setBrojTelefona("+381 1651326");
		osnovniPodaciOLicu.setePosta("mejlpronalazaca@gmail.com");
		pronalazac.setOsnovniPodaciOLicu(osnovniPodaciOLicu);
		return pronalazac;
	}

	private PodaciOPrijavi kreirajPodatkeOPrijavi() {
		PodaciOPrijavi podaciOPrijavi = new PodaciOPrijavi();
		podaciOPrijavi.setBrojOsnovnePrijave("P4324234");
		podaciOPrijavi.setDatumPodnosenjaPrijave(new Date(2000,3,4));
		podaciOPrijavi.setVrstaPrijave("IZDVOJENA");
		return podaciOPrijavi;
	}

	private List<String> kreirajNazivPronalaska() {
		ArrayList<String> nazivi = new ArrayList<>();
		nazivi.add("Leteci auto");
		nazivi.add("Flying car");
		return nazivi;
	}

	private Dostavljanje kreirajDostavljanje() {
		Dostavljanje dostavljanje = new Dostavljanje();
		dostavljanje.setNacinDostave("PAPIRNA_FORM");
		return dostavljanje;
	}

	private PodaciOPodnosiocuPrijave kreirajPodatkeOPodnosiocuPrijave() {
		TFizickoLice podnosilacPrijave = new TFizickoLice();
		podnosilacPrijave.setDrzavljanstvo("Srpsko");
		podnosilacPrijave.setIme("Zika");
		podnosilacPrijave.setPrezime("Zikic");
		OsnovniPodaciOLicu osnovniPodaciOLicu = new OsnovniPodaciOLicu();
		Adresa adresa = new Adresa();
		adresa.setBrojUUlici(3);
		adresa.setDrzava("Srbija");
		adresa.setMesto("Novi Sad");
		adresa.setPostanskiBroj(21000);
		adresa.setUlica("Bulevar Oslobodjenja");
		osnovniPodaciOLicu.setAdresaLica(adresa);
		osnovniPodaciOLicu.setBrojFaksa("33232121321");
		osnovniPodaciOLicu.setBrojTelefona("+381 1231434");
		osnovniPodaciOLicu.setePosta("Mejlmejl@gmial.com");
		podnosilacPrijave.setOsnovniPodaciOLicu(osnovniPodaciOLicu);
		PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = new PodaciOPodnosiocuPrijave();
		podaciOPodnosiocuPrijave.setPodnosilacPrijave(podnosilacPrijave);
		podaciOPodnosiocuPrijave.setPodnosilacPrijaveJeIPronalazac(false);
		return podaciOPodnosiocuPrijave;
	}

	private OsnovneInformacijeOZahtevuZaPriznanjePatenta kreirajOsnovneInformacije() {
		OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setBrojPrijave("P1314");
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(new Date(2000, 1, 1));
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(new Date(2000, 4, 5));
		return osnovneInformacijeOZahtevuZaPriznanjePatenta;
	}

	public static void main(String[] args) {
		PatentTest test = new PatentTest();
		test.test();
	}
	

}
