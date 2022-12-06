import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TPravno_lice")
@XmlType(name= "Pravno_lice", propOrder={"poslovnoIme"})
public class Pravno_lice extends Lice {
    @XmlElement(name="Poslovno_ime", required=true)
    private String poslovnoIme;

    public String getPoslovnoIme() {
        return poslovnoIme;
    }

    public void setPoslovnoIme(String poslovnoIme) {
        this.poslovnoIme = poslovnoIme;
    }
}



