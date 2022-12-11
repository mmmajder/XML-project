package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Adresa", propOrder = {"ulica", "brojUUlici", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name = "Ulica", required = true)
    private String ulica;
    @XmlElement(name = "Broj_u_ulici", required = true)
    private int brojUUlici;
    @XmlElement(name = "Postanski_broj", required = true)
    private int postanskiBroj;
    @XmlElement(name = "Mesto", required = true)
    private String mesto;
    @XmlElement(name = "Drzava", required = true)
    private String drzava;

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBrojUUlici() {
        return brojUUlici;
    }

    public void setBrojUUlici(int brojUUlici) {
        this.brojUUlici = brojUUlici;
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

    @Override
    public String toString() {
        return "Adresa {" +
                "\n\t\t\tulica='" + ulica + '\'' +
                ",\n\t\t\tbrojUUlici=" + brojUUlici +
                ",\n\t\t\tpostanskiBroj=" + postanskiBroj +
                ",\n\t\t\tmesto='" + mesto + '\'' +
                ",\n\t\t\tdrzava='" + drzava + '\'' +
                "\n\t\t}";
    }
}
