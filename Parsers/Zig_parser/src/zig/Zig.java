package zig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Zig", propOrder={"tipZiga", "opisIzgledaZiga", "drugaVrstaZnakaOpis", "izgledPutanjaDoSlike",
        "transliteracijaZnaka", "prevodZnaka", "opisZnaka"})
public class Zig {
    //TODO da li ovde ostaviti String ili Enum tipove?
    @XmlElement(name="Tip_ziga", required=true)
    private String tipZiga;
    @XmlElement(name="Opis_izgleda_ziga", required=true)
    private String opisIzgledaZiga;
    @XmlElement(name="Druga_vrsta_znaka_opis", required=false) // required only if opisIzgledaZiga is DRUGA_VRSTA_ZNAKA
    private String drugaVrstaZnakaOpis;
    @XmlElement(name="Izgled_putanja_do_slike", required=true)
    private String izgledPutanjaDoSlike;
    @XmlElement(name="Transliteracija_znaka", required=true)
    private String transliteracijaZnaka;
    @XmlElement(name="Prevod_znaka", required=true)
    private String prevodZnaka;
    @XmlElement(name="Opis_znaka", required=true)
    private String opisZnaka;

    public String getTipZiga() {
        return tipZiga;
    }

    public void setTipZiga(String tipZiga) {
        this.tipZiga = tipZiga;
    }

    public String getOpisIzgledaZiga() {
        return opisIzgledaZiga;
    }

    public void setOpisIzgledaZiga(String opisIzgledaZiga) {
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
}
