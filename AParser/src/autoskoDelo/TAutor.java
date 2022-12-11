package autoskoDelo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutor", propOrder = {"anoniman", "podaciOAutoru"})
public class TAutor {
    @XmlElement(name = "Anoniman", required = true)
    private boolean anoniman;
    @XmlElement(name = "Podaci_o_autoru", required = true)
    private Autor podaciOAutoru;

    public Autor getPodaciOAutoru() {
        return podaciOAutoru;
    }

    public void setPodaciOAutoru(Autor podaciOAutoru) {
        this.podaciOAutoru = podaciOAutoru;
    }

    public boolean getAnoniman() {
        return anoniman;
    }

    public void setAnoniman(boolean anoniman) {
        this.anoniman = anoniman;
    }

    public boolean isAnoniman() {
        return anoniman;
    }

    @Override
    public String toString() {
        return "TAutor {" +
                "\n\t\tanoniman=" + anoniman +
                ",\n\t\tpodaciOAutoru=" + podaciOAutoru +
                "\n\t}";
    }
}
