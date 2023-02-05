package autoskoDelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Autor", propOrder = {"pseudonim", "autor"})
public class Autor {
    @XmlElement(name = "Pseudonim")
    private String pseudonim;
    @XmlElement(name = "Autor")
    private TLice autor;

    public String getPseudonim() {
        return pseudonim;
    }

    public void setPseudonim(String pseudonim) {
        this.pseudonim = pseudonim;
    }

    public TLice getAutor() {
        return autor;
    }

    public void setAutor(TLice autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Autor {" +
                "\n\t\tpseudonim='" + pseudonim + '\'' +
                ",\n\t\tautor=" + autor +
                "\n\t}";
    }
}
