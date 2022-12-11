package patent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Sadrzaj_zahteva_za_priznanje_patenta")
@XmlType(name="", propOrder={"nazivPronalaska", "podaciOPodnosiocuPrijave", "podaciOPronalazacu", "podaciOPunomocniku", "dostavljanje", "podaciOPrijavi", "zahtevZaPriznanjePrvenstvaIzRanijihPrijava"})
public class SadrzajZahtevaZaPriznanjePatenta {
	@XmlElement(name="Naziv_pronalaska", required=false)
	private List<NazivPronalaska> nazivPronalaska = new ArrayList<NazivPronalaska>();

	@XmlElement(name="Podaci_o_podnosiocu_prijave", required = true)
	private PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave;
	
	@XmlElement(name="Podaci_o_pronalazacu", required = true)
	private PodaciOPronalazacu podaciOPronalazacu;
	
	@XmlElement(name="Podaci_o_punomocniku", required = true)
	private PodaciOPunomocniku podaciOPunomocniku;
	
	@XmlElement(name="Dostavljanje", required = true)
	private Dostavljanje dostavljanje;
	
	@XmlElement(name="Podaci_o_prijavi", required = true)
	private PodaciOPrijavi podaciOPrijavi;
	
	@XmlElementWrapper(name="Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava", required=false)
	@XmlElement(name="Ranija_prijava", required=false)
	private List<RanijaPrijava> zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	
	//TODO

	public PodaciOPodnosiocuPrijave getPodaciOPodnosiocuPrijave() {
		return podaciOPodnosiocuPrijave;
	}

	public void setPodaciOPodnosiocuPrijave(PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave) {
		this.podaciOPodnosiocuPrijave = podaciOPodnosiocuPrijave;
	}
	
	public List<NazivPronalaska> getNazivPronalaska() {
		return nazivPronalaska;
	}

	public void setNazivPronalaska(List<NazivPronalaska> nazivPronalaska) {
		this.nazivPronalaska = nazivPronalaska;
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


	public PodaciOPronalazacu getPodaciOPronalazacu() {
		return podaciOPronalazacu;
	}

	public void setPodaciOPronalazacu(PodaciOPronalazacu podaciOPronalazacu) {
		this.podaciOPronalazacu = podaciOPronalazacu;
	}

	public List<RanijaPrijava> getZahtevZaPriznanjePrvenstvaIzRanijihPrijava() {
		return zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	}

	public void setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(
			List<RanijaPrijava> zahtevZaPriznanjePrvenstvaIzRanijihPrijava) {
		this.zahtevZaPriznanjePrvenstvaIzRanijihPrijava = zahtevZaPriznanjePrvenstvaIzRanijihPrijava;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("- nazivPronalaska: ");
		buffer.append(nazivPronalaska);
		buffer.append("\n- podaciOPodnosiocuPrijave: ");
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
