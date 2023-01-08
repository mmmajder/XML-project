package zig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name= "", propOrder={"adresa", "kontakt"})
public class Lice {
    @XmlElement(name="Adresa", required=true)
    public Adresa adresa;
    @XmlElement(name="Kontakt", required=true)
    public Kontakt kontakt;

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- adresa: ");
		buffer.append(adresa);
		buffer.append("\n\t- kontakt: ");
		buffer.append(kontakt);
		return buffer.toString();
	}
}