import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Klasa", propOrder={"podnosilacPrijave", "punomocnik", "predstavnik", "zig", "klase",
        "zatrazenoPravoPrvenstvaIOsnov", "taksa", "datumPodnosenja", "brojPrijaveZiga"})
public class Zahtev_za_priznanje_ziga {
    @XmlElement(name="Podnosilac_prijave", required=true)
    private List<Lice> podnosilacPrijave;
    @XmlElement(name="Punomocnik", required=true)
    private Lice punomocnik;
    @XmlElement(name="Predstavnik", required=true)
    private Lice predstavnik;
    @XmlElement(name="Zig", required=true)
    private Zig zig;
    @XmlElement(name="Klase", required=true)
    private List<Klasa> klase;
    @XmlElement(name="Zatrazeno_pravo_prvenstva_i_osnov", required=true)
    private String zatrazenoPravoPrvenstvaIOsnov;
    @XmlElement(name="Taksa", required=true)
    private Taksa taksa;
    @XmlElement(name="Datum_podnosenja", required=true)
    private LocalDate datumPodnosenja;
    @XmlElement(name="Broj_prijave_ziga", required=true)
    private String brojPrijaveZiga;

    public List<Lice> getPodnosilacPrijave() {
        return podnosilacPrijave;
    }

    public void setPodnosilacPrijave(List<Lice> podnosilacPrijave) {
        this.podnosilacPrijave = podnosilacPrijave;
    }

    public Lice getPunomocnik() {
        return punomocnik;
    }

    public void setPunomocnik(Lice punomocnik) {
        this.punomocnik = punomocnik;
    }

    public Lice getPredstavnik() {
        return predstavnik;
    }

    public void setPredstavnik(Lice predstavnik) {
        this.predstavnik = predstavnik;
    }

    public Zig getZig() {
        return zig;
    }

    public void setZig(Zig zig) {
        this.zig = zig;
    }

    public List<Klasa> getKlase() {
        return klase;
    }

    public void setKlase(List<Klasa> klase) {
        this.klase = klase;
    }

    public String getZatrazenoPravoPrvenstvaIOsnov() {
        return zatrazenoPravoPrvenstvaIOsnov;
    }

    public void setZatrazenoPravoPrvenstvaIOsnov(String zatrazenoPravoPrvenstvaIOsnov) {
        this.zatrazenoPravoPrvenstvaIOsnov = zatrazenoPravoPrvenstvaIOsnov;
    }

    public Taksa getTaksa() {
        return taksa;
    }

    public void setTaksa(Taksa taksa) {
        this.taksa = taksa;
    }

    public LocalDate getDatumPodnosenja() {
        return datumPodnosenja;
    }

    public void setDatumPodnosenja(LocalDate datumPodnosenja) {
        this.datumPodnosenja = datumPodnosenja;
    }

    public String getBrojPrijaveZiga() {
        return brojPrijaveZiga;
    }

    public void setBrojPrijaveZiga(String brojPrijaveZiga) {
        this.brojPrijaveZiga = brojPrijaveZiga;
    }
}
