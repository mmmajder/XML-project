package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFizicko_Lice", propOrder = {"drzavljanstvo", "osnovniLicniPodaci"})
public class TFizickoLice extends TLice {

    @XmlElement(name = "Drzavljanstvo", required = true)
    private String drzavljanstvo;

    @XmlElement(name = "Osnovni_licni_podaci", required = true)
    private OsnovniLicniPodaci osnovniLicniPodaci;

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

    @Override
    public String toString() {
        return "TFizickoLice{" +
                "drzavljanstvo='" + drzavljanstvo + '\'' +
                ", osnovniLicniPodaci=" + osnovniLicniPodaci +
                '}';
    }
}
