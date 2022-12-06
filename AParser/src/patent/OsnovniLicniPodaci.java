package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Osnovni_licni_podaci")
@XmlType(name = "", propOrder = {"adresa", "ime", "prezime"})
public class OsnovniLicniPodaci {

    @XmlElement(name = "Adresa", required = true)
    private Adresa adresa;
    @XmlElement(name = "Ime", required = true)
    private String ime;
    @XmlElement(name = "Prezime", required = true)
    private String prezime;

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "OsnovniLicniPodaci{" +
                "adresa=" + adresa +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                '}';
    }
}
