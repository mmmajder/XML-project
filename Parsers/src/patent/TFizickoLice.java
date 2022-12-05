package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TFizicko_Lice")
@XmlType(name="TFizicko_Lice", propOrder={"ime", "prezime", "drzavljanstvo"})
public class TFizickoLice extends TLice{
	@XmlElement(name="Ime", required=true)
	private String ime;
	@XmlElement(name="Prezime", required=true)
	private String prezime;
	@XmlElement(name="Drzavljanstvo", required=true)
	private String drzavljanstvo;
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
	public String getDrzavljanstvo() {
		return drzavljanstvo;
	}
	public void setDrzavljanstvo(String drzavljanstvo) {
		this.drzavljanstvo = drzavljanstvo;
	}

	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\t- ime: ");
		buffer.append(ime);
		buffer.append("\t- prezime: ");
		buffer.append(prezime);
		buffer.append("\t- drzavljanstvo: ");
		buffer.append(drzavljanstvo);
		buffer.append(super.toString());
		return buffer.toString();
	}
	
}
