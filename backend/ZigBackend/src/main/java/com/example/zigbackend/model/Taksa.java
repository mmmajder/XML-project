package com.example.zigbackend.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Taksa", propOrder={"osnovnaTaksa", "taksaZaSveKlase", "taksaZaGrafickoResenje"})
public class Taksa {
    @XmlElement(name="Osnovna_taksa", required=true)
    private int osnovnaTaksa;
    @XmlElement(name="Taksa_za_sve_klase", required=true)
    private int taksaZaSveKlase;
    @XmlElement(name="Taksa_za_graficko_resenje", required=true)
    private int taksaZaGrafickoResenje;

    public int getOsnovnaTaksa() {
        return osnovnaTaksa;
    }

    public void setOsnovnaTaksa(int osnovnaTaksa) {
        this.osnovnaTaksa = osnovnaTaksa;
    }

    public int getTaksaZaSveKlase() {
        return taksaZaSveKlase;
    }

    public void setTaksaZaSveKlase(int taksaZaSveKlase) {
        this.taksaZaSveKlase = taksaZaSveKlase;
    }

    public int getTaksaZaGrafickoResenje() {
        return taksaZaGrafickoResenje;
    }

    public void setTaksaZaGrafickoResenje(int taksaZaGrafickoResenje) {
        this.taksaZaGrafickoResenje = taksaZaGrafickoResenje;
    }
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- osnovnaTaksa: ");
		buffer.append(osnovnaTaksa);
		buffer.append("\n\t- taksaZaSveKlase: ");
		buffer.append(taksaZaSveKlase);
		buffer.append("\n\t- taksaZaGrafickoResenje: ");
		buffer.append(taksaZaGrafickoResenje);
		return buffer.toString();
	}
}