import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name= "PrilogPunomocje", propOrder={"punomocjePredatoRanije", "punomocjeCeBitiNaknadnoDostavljeno", "statusPriloga", "putanjaDoPriloga"})
public class PrilogPunomocje {
    @XmlElement(name="Punomocje_predato_ranije", required=true)
    private boolean punomocjePredatoRanije;
    @XmlElement(name="Punomocje_ce_biti_naknadno_dostavljeno", required=true)
    private boolean punomocjeCeBitiNaknadnoDostavljeno;
    @XmlElement(name="Status_priloga")
    private String statusPriloga;
    @XmlElement(name="Putanja_do_priloga")
    private String putanjaDoPriloga;

    public boolean isPunomocjePredatoRanije() {
        return punomocjePredatoRanije;
    }

    public void setPunomocjePredatoRanije(boolean punomocjePredatoRanije) {
        this.punomocjePredatoRanije = punomocjePredatoRanije;
    }

    public boolean isPunomocjeCeBitiNaknadnoDostavljeno() {
        return punomocjeCeBitiNaknadnoDostavljeno;
    }

    public void setPunomocjeCeBitiNaknadnoDostavljeno(boolean punomocjeCeBitiNaknadnoDostavljeno) {
        this.punomocjeCeBitiNaknadnoDostavljeno = punomocjeCeBitiNaknadnoDostavljeno;
    }

    public String getStatusPriloga() {
        return statusPriloga;
    }

    public void setStatusPriloga(String statusPriloga) {
        this.statusPriloga = statusPriloga;
    }

    public String getPutanjaDoPriloga() {
        return putanjaDoPriloga;
    }

    public void setPutanjaDoPriloga(String putanjaDoPriloga) {
        this.putanjaDoPriloga = putanjaDoPriloga;
    }
}