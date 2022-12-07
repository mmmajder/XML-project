package patent;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Sadrzaj_zahteva")
@XmlType(name = "", propOrder = {"podnosilacZahteva", "pseudonimAutora", "podaciOPunomocniku",
        "naslovAutorskogDela", "naslovIAutorZasnivanogDela", "vrstaAutorskogDela",
        "formaZapisaAutorskogDela", "podaciOAutoru", "autorskoDeloStvorenoURadnomOdnosu",
        "nacinKoriscenjaAutorskogDela"})
public class SadrzajZahteva {

    @XmlElement(name = "Podnosilac_zahteva", required = true)
    private TLice podnosilacZahteva;
    @XmlElement(name = "Pseudonim_autora", required = true)
    private String pseudonimAutora;
    @XmlElement(name = "Podaci_o_punomocniku", required = true)
    private String podaciOPunomocniku;
    @XmlElement(name = "Naslov_autorskog_dela", required = true)
    private String naslovAutorskogDela;
    @XmlElement(name = "Naslov_i_autor_zasnivanog_dela", required = true)
    private String naslovIAutorZasnivanogDela;
    @XmlElement(name = "Vrsta_autorskog_dela", required = true)
    private String vrstaAutorskogDela;
    @XmlElement(name = "Forma_zapisa_autorskog_dela", required = true)
    private String formaZapisaAutorskogDela;
    @XmlElement(name = "Podaci_o_autoru", required = true)
    private String podaciOAutoru;
    @XmlElement(name = "Autorsko_delo_stvoreno_u_radnom_odnosu", required = true)
    private boolean autorskoDeloStvorenoURadnomOdnosu;
    @XmlElement(name = "Nacin_koriscenja_autorskog_dela", required = true)
    private String nacinKoriscenjaAutorskogDela;

    public TLice getPodnosilacZahteva() {
        return podnosilacZahteva;
    }

    public void setPodnosilacZahteva(TLice podnosilacZahteva) {
        this.podnosilacZahteva = podnosilacZahteva;
    }

    public String getPseudonimAutora() {
        return pseudonimAutora;
    }

    public void setPseudonimAutora(String pseudonimAutora) {
        this.pseudonimAutora = pseudonimAutora;
    }

    public String getPodaciOPunomocniku() {
        return podaciOPunomocniku;
    }

    public void setPodaciOPunomocniku(String podaciOPunomocniku) {
        this.podaciOPunomocniku = podaciOPunomocniku;
    }

    public String getNaslovAutorskogDela() {
        return naslovAutorskogDela;
    }

    public void setNaslovAutorskogDela(String naslovAutorskogDela) {
        this.naslovAutorskogDela = naslovAutorskogDela;
    }

    public String getNaslovIAutorZasnivanogDela() {
        return naslovIAutorZasnivanogDela;
    }

    public void setNaslovIAutorZasnivanogDela(String naslovIAutorZasnivanogDela) {
        this.naslovIAutorZasnivanogDela = naslovIAutorZasnivanogDela;
    }

    public String getVrstaAutorskogDela() {
        return vrstaAutorskogDela;
    }

    public void setVrstaAutorskogDela(String vrstaAutorskogDela) {
        this.vrstaAutorskogDela = vrstaAutorskogDela;
    }

    public String getFormaZapisaAutorskogDela() {
        return formaZapisaAutorskogDela;
    }

    public void setFormaZapisaAutorskogDela(String formaZapisaAutorskogDela) {
        this.formaZapisaAutorskogDela = formaZapisaAutorskogDela;
    }

    public String getPodaciOAutoru() {
        return podaciOAutoru;
    }

    public void setPodaciOAutoru(String podaciOAutoru) {
        this.podaciOAutoru = podaciOAutoru;
    }

    public boolean isAutorskoDeloStvorenoURadnomOdnosu() {
        return autorskoDeloStvorenoURadnomOdnosu;
    }

    public void setAutorskoDeloStvorenoURadnomOdnosu(boolean autorskoDeloStvorenoURadnomOdnosu) {
        this.autorskoDeloStvorenoURadnomOdnosu = autorskoDeloStvorenoURadnomOdnosu;
    }

    public String getNacinKoriscenjaAutorskogDela() {
        return nacinKoriscenjaAutorskogDela;
    }

    public void setNacinKoriscenjaAutorskogDela(String nacinKoriscenjaAutorskogDela) {
        this.nacinKoriscenjaAutorskogDela = nacinKoriscenjaAutorskogDela;
    }

    @Override
    public String toString() {
        return "SadrzajZahteva{" +
                "podnosilacZahteva=" + podnosilacZahteva +
                ", pseudonimAutora='" + pseudonimAutora + '\'' +
                ", podaciOPunomocniku='" + podaciOPunomocniku + '\'' +
                ", naslovAutorskogDela='" + naslovAutorskogDela + '\'' +
                ", naslovIAutorZasnivanogDela='" + naslovIAutorZasnivanogDela + '\'' +
                ", vrstaAutorskogDela='" + vrstaAutorskogDela + '\'' +
                ", formaZapisaAutorskogDela='" + formaZapisaAutorskogDela + '\'' +
                ", podaciOAutoru='" + podaciOAutoru + '\'' +
                ", autorskoDeloStvorenoURadnomOdnosu=" + autorskoDeloStvorenoURadnomOdnosu +
                ", nacinKoriscenjaAutorskogDela='" + nacinKoriscenjaAutorskogDela + '\'' +
                '}';
    }
}
