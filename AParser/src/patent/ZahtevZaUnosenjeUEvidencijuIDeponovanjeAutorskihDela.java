package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "Zahtev_za_unosenje_u_evidenciju_i_deponovanje_autorskih_dela"
)
@XmlType(
        name = "",
        propOrder = {"informacijeOZahtevu", "sadrzajZahteva"}
)
public class ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela {
    @XmlElement(
            name = "Informacije_o_zahtevu",
            required = true
    )
    private InformacijeOZahtevu informacijeOZahtevu;
    @XmlElement(
            name = "Sadrzaj_zahteva",
            required = true
    )
    private SadrzajZahteva sadrzajZahteva;

    public InformacijeOZahtevu getInformacijeOZahtevu() {
        return informacijeOZahtevu;
    }

    public void setInformacijeOZahtevu(InformacijeOZahtevu informacijeOZahtevu) {
        this.informacijeOZahtevu = informacijeOZahtevu;
    }

    public SadrzajZahteva getSadrzajZahteva() {
        return sadrzajZahteva;
    }

    public void setSadrzajZahteva(SadrzajZahteva sadrzajZahteva) {
        this.sadrzajZahteva = sadrzajZahteva;
    }

    @Override
    public String toString() {
        return "ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela{" +
                "informacijeOZahtevu=" + informacijeOZahtevu +
                ", sadrzajZahteva=" + sadrzajZahteva +
                '}';
    }
}
