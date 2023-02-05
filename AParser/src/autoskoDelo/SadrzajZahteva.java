package autoskoDelo;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Sadrzaj_zahteva")
@XmlType(name = "Sadrzaj_zahteva", propOrder = {"podnosilacZahteva", "autori", "podaciOPunomocniku", "autorskoDelo"})
public class SadrzajZahteva {

    @XmlElement(name = "Podnosilac_zahteva", required = true)
    private PodnosilacZahteva podnosilacZahteva;
    @XmlElement(name = "Podaci_o_punomocniku", required = true)
    private OsnovniLicniPodaci podaciOPunomocniku;
    @XmlElement(name = "Autori", required = true)
    private ArrayList<TAutor> autori;
    @XmlElement(name = "Autorsko_delo", required = true)
    private AutorskoDelo autorskoDelo;

    public PodnosilacZahteva getPodnosilacZahteva() {
        return podnosilacZahteva;
    }

    public void setPodnosilacZahteva(PodnosilacZahteva podnosilacZahteva) {
        this.podnosilacZahteva = podnosilacZahteva;
    }

    public OsnovniLicniPodaci getPodaciOPunomocniku() {
        return podaciOPunomocniku;
    }

    public void setPodaciOPunomocniku(OsnovniLicniPodaci podaciOPunomocniku) {
        this.podaciOPunomocniku = podaciOPunomocniku;
    }

    public AutorskoDelo getAutorskoDelo() {
        return autorskoDelo;
    }

    public void setAutorskoDelo(AutorskoDelo autorskoDelo) {
        this.autorskoDelo = autorskoDelo;
    }

    public ArrayList<TAutor> getAutori() {
        return autori;
    }

    public void setAutori(ArrayList<TAutor> autori) {
        this.autori = autori;
    }

    @Override
    public String toString() {
        return "SadrzajZahteva {" +
                "\n\tpodnosilacZahteva=" + podnosilacZahteva +
                ",\n\tpodaciOPunomocniku=" + podaciOPunomocniku +
                ",\n\tautori=" + autori +
                ",\n\tautorskoDelo=" + autorskoDelo +
                "\n}";
    }
}
