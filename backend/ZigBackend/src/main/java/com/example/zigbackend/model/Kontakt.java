package com.example.zigbackend.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Kontakt", propOrder={"telefon", "email", "faks"})
public class Kontakt {


    @XmlElement(name="Telefon", required=true)
    private String telefon;
    @XmlElement(name="Email", required=true)
    private String email;
    @XmlElement(name="Faks", required=true)
    private String faks;

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaks() {
        return faks;
    }

    public void setFaks(String faks) {
        this.faks = faks;
    }

    public Kontakt() {
    }

    public Kontakt(String telefon, String email, String faks) {
        this.telefon = telefon;
        this.email = email;
        this.faks = faks;
    }

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t\t- telefon: ");
		buffer.append(telefon);
		buffer.append("\n\t\t- email: ");
		buffer.append(email);
		buffer.append("\n\t\t- faks: ");
		buffer.append(faks);
		return buffer.toString();
	}
}
