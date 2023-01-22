package com.example.zigbackend.model;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TFizicko_Lice", propOrder={"ime", "prezime"})
public class FizickoLice extends Lice {
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
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\n\t- ime: ");
		buffer.append(ime);
		buffer.append("\n\t- prezime: ");
		buffer.append(prezime);
		return buffer.toString();
	}
}


