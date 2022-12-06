import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Taksa", propOrder={"osnovnaTaksa", "taksaZaSveKlase", "taksaZaGrafickoResenje"})
public class Taksa {
    @XmlElement(name="Osnovna_taksa", required=true)
    private int osnovnaTaksa;
    @XmlElement(name="Taksa_za_sve_klase", required=true)
    private int taksaZaSveKlase;
    @XmlElement(name="Taksa_za_graficko_resenje", required=true)
    private int taksaZaGrafickoResenje;
}