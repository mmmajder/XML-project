package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFizicko_Lice", propOrder = {"drzavljanstvo", "osnovniLicniPodaci", "godinaSmrti"})
public class TFizickoLice extends TLice {

    @XmlElement(name = "Drzavljanstvo", required = true)
    private String drzavljanstvo;

    @XmlElement(name = "Osnovni_licni_podaci", required = true)
    private OsnovniLicniPodaci osnovniLicniPodaci;

    @XmlElement(name = "Godina_smrti")
    private int godinaSmrti;

    public OsnovniLicniPodaci getOsnovniLicniPodaci() {
        return osnovniLicniPodaci;
    }

    public void setOsnovniLicniPodaci(OsnovniLicniPodaci osnovniLicniPodaci) {
        this.osnovniLicniPodaci = osnovniLicniPodaci;
    }

    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public int getGodinaSmrti() {
        return godinaSmrti;
    }

    public void setGodinaSmrti(int godinaSmrti) {
        this.godinaSmrti = godinaSmrti;
    }

    @Override
    public String toString() {
        return "TFizickoLice {" +
                "\n\t\t\tdrzavljanstvo='" + drzavljanstvo + '\'' +
                ",\n\t\t\tosnovniLicniPodaci=" + osnovniLicniPodaci +
                ",\n\t\t\tgodinaSmrti=" + godinaSmrti +
                "\n\t\t}";
    }
}
