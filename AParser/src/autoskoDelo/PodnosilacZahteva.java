package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Podnosilac_zahteva", propOrder = {"podnosilacJeIAutor", "podaciOPodnosiocu"})
public class PodnosilacZahteva {

    @XmlElement(name = "Podnosilac_je_i_autor", required = true)
    private boolean podnosilacJeIAutor;
    @XmlElement(name = "Podaci_o_podnosiocu")
    private TLice podaciOPodnosiocu;

    public boolean isPodnosilacJeIAutor() {
        return podnosilacJeIAutor;
    }

    public void setPodnosilacJeIAutor(boolean podnosilacJeIAutor) {
        this.podnosilacJeIAutor = podnosilacJeIAutor;
    }

    public TLice getPodaciOPodnosiocu() {
        return podaciOPodnosiocu;
    }

    public void setPodaciOPodnosiocu(TLice podaciOPodnosiocu) {
        this.podaciOPodnosiocu = podaciOPodnosiocu;
    }

    @Override
    public String toString() {
        return "PodnosilacZahteva {" +
                "\n\t\tpodnosilacJeIAutor=" + podnosilacJeIAutor +
                ",\n\t\tpodaciOPodnosiocu=" + podaciOPodnosiocu +
                "\n\t}";
    }
}
