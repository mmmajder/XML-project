package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zasnivano_delo")
@XmlType(name = "Zasnivano_delo", propOrder = {"naslov", "autor"})
public class ZasnivanoDelo {
    private String naslov;
    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    @Override
    public String toString() {
        return "ZasnivanoDelo {" +
                "\n\tnaslov='" + naslov + '\'' +
                ",\n\tautor='" + autor + '\'' +
                "\n}";
    }
}
