import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

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

//    @Override
//    public String toString() {
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("\n\t- ulica: ");
//        buffer.append(ulica);
//        buffer.append("\n\t- broj: ");
//        buffer.append(broj);
//        buffer.append("\n\t- postanskiBroj: ");
//        buffer.append(postanskiBroj);
//        buffer.append("\n\t- mesto: ");
//        buffer.append(mesto);
//        buffer.append("\n\t- drzava: ");
//        buffer.append(drzava);
//        return buffer.toString();
//    }

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
}
