import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TFizicko_Lice")
@XmlType(name="TFizicko_Lice", propOrder={"ime", "prezime"})
public class Fizicko_lice extends Lice {
    @XmlElement(name="Ime", required=true)
    private String ime;
    @XmlElement(name="Prezime", required=true)
    private String prezime;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}

