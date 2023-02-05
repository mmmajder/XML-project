package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import patent.Adresa;
import patent.Dostavljanje;
import patent.NazivPronalaska;
import patent.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import patent.PodaciOPodnosiocuPrijave;
import patent.PodaciOPrijavi;
import patent.PodaciOPronalazacu;
import patent.PodaciOPunomocniku;
import patent.RanijaPrijava;
import patent.SadrzajZahtevaZaPriznanjePatenta;
import patent.TFizickoLice;
import patent.TPravnoLice;
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
			ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = (ZahtevZaPriznanjePatenta) unmarshaller.unmarshal(new File("./data/Patent-primer.xml"));
			
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
			marshaller.marshal(zahtevZaPriznanjePatenta, new File("marshalling-patent.xml"));
			
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

	private List<RanijaPrijava> kreirajZahtevZaPriznanjePrvenstva() {
		RanijaPrijava ranijaPrijava1 = new RanijaPrijava();
		ranijaPrijava1.setBrojRanijePrijave("P-1231/2019");
		ranijaPrijava1.setDatumaPodnosenjaPrijave(new Date(2000,2,2));
		ranijaPrijava1.setDvoslovnaOznaka("NS");
		
		RanijaPrijava ranijaPrijava2 = new RanijaPrijava();
		ranijaPrijava2.setBrojRanijePrijave("P-32312/2019");
		ranijaPrijava2.setDatumaPodnosenjaPrijave(new Date(2000,5,2));
		ranijaPrijava2.setDvoslovnaOznaka("NS");
		
		ArrayList<RanijaPrijava> listaRanijihPrijava = new ArrayList<>();
		listaRanijihPrijava.add(ranijaPrijava1);
		listaRanijihPrijava.add(ranijaPrijava2);
		return listaRanijihPrijava;
	}

	private PodaciOPunomocniku kreirajPodatkeOPunomocniku() {
		TPravnoLice punomocnik = new TPravnoLice();
		Adresa adresa = new Adresa();
		adresa.setBrojUUlici(3);
		adresa.setDrzava("Srbija");
		adresa.setMesto("Novi Sad");
		adresa.setPostanskiBroj(21000);
		adresa.setUlica("Bulevar Evrope");
		punomocnik.setAdresaLica(adresa);
		punomocnik.setBrojFaksa("321321");
		punomocnik.setBrojTelefona("+381 12315466");
		punomocnik.setePosta("Mejlpunomocnika@gmial.com");
		punomocnik.setPoslovnoIme("Sverc komerc");
		PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
		podaciOPunomocniku.setPunomocnik(punomocnik);
		podaciOPunomocniku.setVrstaPunomocnika("PUNOMOCNIK_ZA_PRIJEM_PISMENA");
		podaciOPunomocniku.setZajednickiPredstavnik(false);
		return podaciOPunomocniku;
	}

	private PodaciOPronalazacu kreirajPotatkeOPronalazacu() {
		PodaciOPronalazacu podaciOPronalazacu = new PodaciOPronalazacu();
		podaciOPronalazacu.setPronalazacNeZeliDaBudeNaveden(false);
		TFizickoLice pronalazac = new TFizickoLice();
		pronalazac.setDrzavljanstvo("Srpsko");
		pronalazac.setIme("Pera");
		pronalazac.setPrezime("Peric");
		Adresa adresaLica= new Adresa();
		adresaLica.setBrojUUlici(2);
		adresaLica.setDrzava("Crna Gora");
		adresaLica.setMesto("Niksic");
		adresaLica.setPostanskiBroj(432423);
		adresaLica.setUlica("Bla bla");
		pronalazac.setAdresaLica(adresaLica);
		pronalazac.setBrojFaksa("4234234");
		pronalazac.setBrojTelefona("+381 1651326");
		pronalazac.setePosta("mejlpronalazaca@gmail.com");
		podaciOPronalazacu.setPronalazac(pronalazac);
		return podaciOPronalazacu;
	}

	private PodaciOPrijavi kreirajPodatkeOPrijavi() {
		PodaciOPrijavi podaciOPrijavi = new PodaciOPrijavi();
		podaciOPrijavi.setBrojOsnovnePrijave("P-432/2022");
		podaciOPrijavi.setDatumPodnosenjaPrijave(new Date(2000,3,4));
		podaciOPrijavi.setVrstaPrijave("IZDVOJENA");
		return podaciOPrijavi;
	}

	private List<NazivPronalaska> kreirajNazivPronalaska() {
		ArrayList<NazivPronalaska> nazivi = new ArrayList<>();
		NazivPronalaska nazivPronalaska = new NazivPronalaska();
		nazivPronalaska.setJezik("SRPSKI");
		nazivPronalaska.setNaziv("Novi izum");
		NazivPronalaska nazivPronalaska2 = new NazivPronalaska();
		nazivPronalaska2.setJezik("ENGLESKI");
		nazivPronalaska2.setNaziv("New invention");
		nazivi.add(nazivPronalaska);
		nazivi.add(nazivPronalaska2);
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
		Adresa adresa = new Adresa();
		adresa.setBrojUUlici(3);
		adresa.setDrzava("Srbija");
		adresa.setMesto("Novi Sad");
		adresa.setPostanskiBroj(21000);
		adresa.setUlica("Bulevar Oslobodjenja");
		podnosilacPrijave.setAdresaLica(adresa);
		podnosilacPrijave.setBrojFaksa("33232121321");
		podnosilacPrijave.setBrojTelefona("+381 1231434");
		podnosilacPrijave.setePosta("Mejlmejl@gmial.com");
		PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = new PodaciOPodnosiocuPrijave();
		podaciOPodnosiocuPrijave.setPodnosilacPrijave(podnosilacPrijave);
		podaciOPodnosiocuPrijave.setPodnosilacPrijaveJeIPronalazac(false);
		return podaciOPodnosiocuPrijave;
	}

	private OsnovneInformacijeOZahtevuZaPriznanjePatenta kreirajOsnovneInformacije() {
		OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setBrojPrijave("P-1314/2019");
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(new Date(2000, 1, 1));
		osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(new Date(2000, 4, 5));
		return osnovneInformacijeOZahtevuZaPriznanjePatenta;
	}

	public static void main(String[] args) {
		PatentTest test = new PatentTest();
		test.test();
	}
	

}
