package autoskoDelo;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Informacije_o_zahtevu")
@XmlType(name = "Informacije_o_zahtevu", propOrder = {"brojPrijave", "datumPodnosenja", "listaPrilogaKojiSePodnoseUzZahtev"})
public class InformacijeOZahtevu {
    @XmlElement(name = "Broj_prijave", required = true)
    private String brojPrijave;
    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumPodnosenja;
    @XmlElement(name = "Lista_priloga_koji_se_podnose_uz_zahtev")
    @XmlSchemaType(name = "list")
    private ArrayList<String> listaPrilogaKojiSePodnoseUzZahtev;

    public String getBrojPrijave() {
        return brojPrijave;
    }

    public Date getDatumPodnosenja() {
        return datumPodnosenja;
    }

    public void setDatumPodnosenja(Date datumPodnosenja) {
        this.datumPodnosenja = datumPodnosenja;
    }

    public ArrayList<String> getListaPrilogaKojiSePodnoseUzZahtev() {
        return listaPrilogaKojiSePodnoseUzZahtev;
    }

    public void setListaPrilogaKojiSePodnoseUzZahtev(ArrayList<String> listaPrilogaKojiSePodnoseUzZahtev) {
        this.listaPrilogaKojiSePodnoseUzZahtev = listaPrilogaKojiSePodnoseUzZahtev;
    }

    public void setBrojPrijave(String brojPrijave) {
        this.brojPrijave = brojPrijave;
    }

    @Override
    public String toString() {
        return "OsnovneInformacijeOZahtevu {" +
                "\n\tbrojPrijave='" + brojPrijave + '\'' +
                ",\n\tdatumPodnosenja=" + datumPodnosenja +
                ",\n\tlistaPrilogaKojiSePodnoseUzZahtev=" + listaPrilogaKojiSePodnoseUzZahtev +
                "\n}";
    }
}
