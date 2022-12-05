package patent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Sadrzaj_zahteva_za_priznanje_patenta")
@XmlType(name="", propOrder={"nazivPronalaska", "podaciOPodnosiocuPrijave", "podaciOPronalazacu", "podaciOPunomocniku", "dostavljanje", "podaciOPrijavi", "zahtevZaPriznanjePrvenstvaIzRanijihPrijava"})
public class SadrzajZahtevaZaPriznanjePatenta {
	@XmlElement(name="Naziv_pronalaska", required=false)
	private List<String> nazivPronalaska = new ArrayList<String>();

	
	@XmlElement(name="Podaci_o_podnosiocu_prijave", required = true)
	private PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave;
	
	@XmlElement(name="Podaci_o_pronalazacu", required = true)
	private TFizickoLice podaciOPronalazacu;
	
	@XmlElement(name="Podaci_o_punomocniku", required = true)
	private PodaciOPunomocniku podaciOPunomocniku;
	
	@XmlElement(name="Dostavljanje", required = true)
	private Dostavljanje dostavljanje;
	
	@XmlElement(name="Podaci_o_prijavi", required = true)
	private PodaciOPrijavi podaciOPrijavi;
	
	@XmlElement(name="Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava", required = true)
	private ZahtevZaPriznanjePrvenstvaIzRanijihPrijava zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	
	//TODO

	public PodaciOPodnosiocuPrijave getPodaciOPodnosiocuPrijave() {
		return podaciOPodnosiocuPrijave;
	}

	public void setPodaciOPodnosiocuPrijave(PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave) {
		this.podaciOPodnosiocuPrijave = podaciOPodnosiocuPrijave;
	}
	
	public List<String> getNazivPronalaska() {
		return nazivPronalaska;
	}

	public void setNazivPronalaska(List<String> nazivPronalaska) {
		this.nazivPronalaska = nazivPronalaska;
	}

	public TFizickoLice getPodaciOPronalazacu() {
		return podaciOPronalazacu;
	}

	public void setPodaciOPronalazacu(TFizickoLice podaciOPronalazacu) {
		this.podaciOPronalazacu = podaciOPronalazacu;
	}

	public PodaciOPunomocniku getPodaciOPunomocniku() {
		return podaciOPunomocniku;
	}

	public void setPodaciOPunomocniku(PodaciOPunomocniku podaciOPunomocniku) {
		this.podaciOPunomocniku = podaciOPunomocniku;
	}

	public Dostavljanje getDostavljanje() {
		return dostavljanje;
	}

	public void setDostavljanje(Dostavljanje dostavljanje) {
		this.dostavljanje = dostavljanje;
	}

	public PodaciOPrijavi getPodaciOPrijavi() {
		return podaciOPrijavi;
	}

	public void setPodaciOPrijavi(PodaciOPrijavi podaciOPrijavi) {
		this.podaciOPrijavi = podaciOPrijavi;
	}

	public ZahtevZaPriznanjePrvenstvaIzRanijihPrijava getZahtevZaPriznanjePrvenstvaIzRanijihPrijava() {
		return zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	}

	public void setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(
			ZahtevZaPriznanjePrvenstvaIzRanijihPrijava zahtevZaPriznanjePrvenstvaIzRanijihPrijava) {
		this.zahtevZaPriznanjePrvenstvaIzRanijihPrijava = zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("- nazivPronalaska: ");
		buffer.append(nazivPronalaska);
		buffer.append("- podaciOPodnosiocuPrijave: ");
		buffer.append(podaciOPodnosiocuPrijave);
		buffer.append("\n- podaciOPronalazacu: ");
		buffer.append(podaciOPronalazacu);
		buffer.append("\n- podaciOPunomocniku: ");
		buffer.append(podaciOPunomocniku);
		buffer.append("\n- dostavljanje: ");
		buffer.append(dostavljanje);
		buffer.append("\n- podaciOPrijavi: ");
		buffer.append(podaciOPrijavi);
		buffer.append("\n- zahtevZaPriznanjePrvenstvaIzRanijihPrijava: ");
		buffer.append(zahtevZaPriznanjePrvenstvaIzRanijihPrijava);
		return buffer.toString();
	}
}
