package zig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Adresa", propOrder={"ulica", "broj", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name="Ulica", required=true)
    private String ulica;
    @XmlElement(name="Broj", required=true)
    private int broj;
    @XmlElement(name="Postanski_broj", required=true)
    private int postanskiBroj;
    @XmlElement(name="Mesto", required=true)
    private String mesto;
    @XmlElement(name="Drzava", required=true)
    private String drzava;

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public Adresa() {
    }

    public Adresa(String ulica, int broj, int postanskiBroj, String mesto, String drzava) {
        this.ulica = ulica;
        this.broj = broj;
        this.postanskiBroj = postanskiBroj;
        this.mesto = mesto;
        this.drzava = drzava;
    }

}
