package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TPravno_Lice", propOrder={"poslovnoIme"})
public class TPravnoLice extends TLice {
	@XmlElement(name="Poslovno_ime", required=true)
	private String poslovnoIme;

	public String getPoslovnoIme() {
		return poslovnoIme;
	}

	public void setPoslovnoIme(String poslovnoIme) {
		this.poslovnoIme = poslovnoIme;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t- poslovnoIme: ");
		buffer.append(poslovnoIme);
		buffer.append(super.toString());
		return buffer.toString();
	}
	
}
