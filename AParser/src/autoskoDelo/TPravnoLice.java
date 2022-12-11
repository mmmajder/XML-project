package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TPravno_lice")
@XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme", "sedisteNosiocaAutorskogPrava", "osnovniLicniPodaci"})
public class TPravnoLice extends TLice {

    @XmlElement(name = "Poslovno_ime", required = true)
    private String poslovnoIme;

    @XmlElement(name = "Osnovni_licni_podaci", required = true)
    private OsnovniLicniPodaci osnovniLicniPodaci;

    @XmlElement(name = "Sediste_nosioca_autorskog_prava", required = true)
    private Adresa sedisteNosiocaAutorskogPrava;

    public OsnovniLicniPodaci getOsnovniLicniPodaci() {
        return osnovniLicniPodaci;
    }

    public void setOsnovniLicniPodaci(OsnovniLicniPodaci osnovniLicniPodaci) {
        this.osnovniLicniPodaci = osnovniLicniPodaci;
    }

    public String getPoslovnoIme() {
        return poslovnoIme;
    }

    public void setPoslovnoIme(String poslovnoIme) {
        this.poslovnoIme = poslovnoIme;
    }

    public Adresa getSedisteNosiocaAutorskogPrava() {
        return sedisteNosiocaAutorskogPrava;
    }

    public void setSedisteNosiocaAutorskogPrava(Adresa sedisteNosiocaAutorskogPrava) {
        this.sedisteNosiocaAutorskogPrava = sedisteNosiocaAutorskogPrava;
    }

    @Override
    public String toString() {
        return "TPravnoLice {" +
                "\n\tposlovnoIme='" + poslovnoIme + '\'' +
                ",\n\tosnovniLicniPodaci=" + osnovniLicniPodaci +
                ",\n\tsedisteNosiocaAutorskogPrava=" + sedisteNosiocaAutorskogPrava +
                "\n}";
    }
}
