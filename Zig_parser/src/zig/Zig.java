package zig;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Zig", propOrder={"tipZiga", "opisIzgledaZiga", "drugaVrstaZnakaOpis", "izgledPutanjaDoSlike", "boja",
        "transliteracijaZnaka", "prevodZnaka", "opisZnaka"})
public class Zig {
    //TODO da li ovde ostaviti String ili Enum tipove?
    @XmlElement(name="Tip_ziga", required=true)
    private ETip_ziga tipZiga;
    @XmlElement(name="Opis_izgleda_ziga", required=true)
    private EOpis_izgleda_ziga opisIzgledaZiga;
    @XmlElement(name="Druga_vrsta_znaka_opis", required=false) // required only if opisIzgledaZiga is DRUGA_VRSTA_ZNAKA
    private String drugaVrstaZnakaOpis;
    @XmlElement(name="Izgled_putanja_do_slike", required=true)
    private String izgledPutanjaDoSlike;
    @XmlElement(name="Boja", required=true)
    private List<EBoja> boja;
    @XmlElement(name="Transliteracija_znaka", required=true)
    private String transliteracijaZnaka;
    @XmlElement(name="Prevod_znaka", required=true)
    private String prevodZnaka;
    @XmlElement(name="Opis_znaka", required=true)
    private String opisZnaka;

    public ETip_ziga getTipZiga() {
        return tipZiga;
    }

    public void setTipZiga(ETip_ziga tipZiga) {
        this.tipZiga = tipZiga;
    }

    public EOpis_izgleda_ziga getOpisIzgledaZiga() {
        return opisIzgledaZiga;
    }

    public void setOpisIzgledaZiga(EOpis_izgleda_ziga opisIzgledaZiga) {
        this.opisIzgledaZiga = opisIzgledaZiga;
    }

    public String getDrugaVrstaZnakaOpis() {
        return drugaVrstaZnakaOpis;
    }

    public void setDrugaVrstaZnakaOpis(String drugaVrstaZnakaOpis) {
        this.drugaVrstaZnakaOpis = drugaVrstaZnakaOpis;
    }

    public String getIzgledPutanjaDoSlike() {
        return izgledPutanjaDoSlike;
    }

    public void setIzgledPutanjaDoSlike(String izgledPutanjaDoSlike) {
        this.izgledPutanjaDoSlike = izgledPutanjaDoSlike;
    }

    public String getTransliteracijaZnaka() {
        return transliteracijaZnaka;
    }

    public void setTransliteracijaZnaka(String transliteracijaZnaka) {
        this.transliteracijaZnaka = transliteracijaZnaka;
    }

    public String getPrevodZnaka() {
        return prevodZnaka;
    }

    public void setPrevodZnaka(String prevodZnaka) {
        this.prevodZnaka = prevodZnaka;
    }

    public String getOpisZnaka() {
        return opisZnaka;
    }

    public void setOpisZnaka(String opisZnaka) {
        this.opisZnaka = opisZnaka;
    }
	
	public List<EBoja> getBoja() {
		return boja;
	}

	public void setBoja(List<EBoja> boja) {
		this.boja = boja;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- tipZiga: ");
		buffer.append(tipZiga);
		buffer.append("\n\t- opisIzgledaZiga: ");
		buffer.append(opisIzgledaZiga);
		buffer.append("\n\t- drugaVrstaZnakaOpis: ");
		buffer.append(drugaVrstaZnakaOpis);
		buffer.append("\n\t- izgledPutanjaDoSlike: ");
		buffer.append(izgledPutanjaDoSlike);
		buffer.append("\n\t- boje: ");
		
		for (EBoja posebna_boja : boja) {
			buffer.append("\n\t\t- " + posebna_boja);
		}
		
		buffer.append("\n\t- transliteracijaZnaka: ");
		buffer.append(transliteracijaZnaka);
		buffer.append("\n\t- prevodZnaka: ");
		buffer.append(prevodZnaka);
		buffer.append("\n\t- opisZnaka: ");
		buffer.append(opisZnaka);
		return buffer.toString();
	}
}
