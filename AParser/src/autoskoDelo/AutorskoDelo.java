package autoskoDelo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Autorsko_delo")
@XmlType(name = "Autorsko_delo", propOrder = {"naslovAutorskogDela", "zasnivanoDelo", "vrstaAutorskogDela",
        "formaZapisaAutorskogDela", "autorskoDeloStvorenoURadnomOdnosu", "nacinKoriscenjaAutorskogDela"})
public class AutorskoDelo {
    @XmlElement(name = "Naslov_autorskog_dela", required = true)
    private String naslovAutorskogDela;
    @XmlElement(name = "Zasnivano_delo")
    private ZasnivanoDelo zasnivanoDelo;
    @XmlElement(name = "Vrsta_autorskog_dela", required = true)
    private String vrstaAutorskogDela;
    @XmlElement(name = "Forma_zapisa_autorskog_dela", required = true)
    private String formaZapisaAutorskogDela;
    @XmlElement(name = "Autorsko_delo_stvoreno_u_radnom_odnosu", required = true)
    private boolean autorskoDeloStvorenoURadnomOdnosu;
    @XmlElement(name = "Nacin_koriscenja_autorskog_dela", required = true)
    private String nacinKoriscenjaAutorskogDela;

    public String getNaslovAutorskogDela() {
        return naslovAutorskogDela;
    }

    public void setNaslovAutorskogDela(String naslovAutorskogDela) {
        this.naslovAutorskogDela = naslovAutorskogDela;
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

    public ZasnivanoDelo getZasnivanoDelo() {
        return zasnivanoDelo;
    }

    public void setZasnivanoDelo(ZasnivanoDelo zasnivanoDelo) {
        this.zasnivanoDelo = zasnivanoDelo;
    }

    @Override
    public String toString() {
        return "AutorskoDelo {" +
                "\n\t\tnaslovAutorskogDela='" + naslovAutorskogDela + '\'' +
                ",\n\t\tzasnivanoDelo=" + zasnivanoDelo +
                ",\n\t\tvrstaAutorskogDela='" + vrstaAutorskogDela + '\'' +
                ",\n\t\tformaZapisaAutorskogDela='" + formaZapisaAutorskogDela + '\'' +
                ",\n\t\tautorskoDeloStvorenoURadnomOdnosu=" + autorskoDeloStvorenoURadnomOdnosu +
                ",\n\t\tnacinKoriscenjaAutorskogDela='" + nacinKoriscenjaAutorskogDela + '\'' +
                "\n\t}";
    }
}
