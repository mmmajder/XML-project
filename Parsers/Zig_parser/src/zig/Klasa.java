package zig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Klasa", propOrder={"idKlase", "punNazivKlase"})
public class Klasa {
    @XmlElement(name="Id_klase", required=true)
    private String idKlase;
    @XmlElement(name="Pun_naziv_klase", required=true)
    private String punNazivKlase;

    public String getIdKlase() {
        return idKlase;
    }

    public void setIdKlase(String idKlase) {
        this.idKlase = idKlase;
    }

    public String getPunNazivKlase() {
        return punNazivKlase;
    }

    public void setPunNazivKlase(String punNazivKlase) {
        this.punNazivKlase = punNazivKlase;
    }


}
