import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Prilog", propOrder={"tipPriloga", "statusPriloga", "putanjaDoPriloga"})
public class Prilog {
    @XmlElement(name="Tip_priloga", required=true)
    private String tipPriloga;
    @XmlElement(name="Status_priloga", required=true)
    private int statusPriloga;
    @XmlElement(name="Putanja_do_priloga")
    private int putanjaDoPriloga;

    public String getTipPriloga() {
        return tipPriloga;
    }

    public void setTipPriloga(String tipPriloga) {
        this.tipPriloga = tipPriloga;
    }

    public int getStatusPriloga() {
        return statusPriloga;
    }

    public void setStatusPriloga(int statusPriloga) {
        this.statusPriloga = statusPriloga;
    }

    public int getPutanjaDoPriloga() {
        return putanjaDoPriloga;
    }

    public void setPutanjaDoPriloga(int putanjaDoPriloga) {
        this.putanjaDoPriloga = putanjaDoPriloga;
    }
}