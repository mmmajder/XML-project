package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Adresa", propOrder={"ulica", "broj", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name="Ulica", required=true)
    public String ulica;
    @XmlElement(name="Broj", required=true)
    public int broj;
    @XmlElement(name="Postanski_broj", required=true)
    public int postanskiBroj;
    @XmlElement(name="Mesto", required=true)
    public String mesto;
    @XmlElement(name="Drzava", required=true)
    public String drzava;

    public Adresa(String ulica, int broj, int postanskiBroj, String mesto, String drzava) {
        this.ulica = ulica;
        this.broj = broj;
        this.postanskiBroj = postanskiBroj;
        this.mesto = mesto;
        this.drzava = drzava;
    }

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t\t- ulica: ");
		buffer.append(ulica);
		buffer.append("\n\t\t- broj: ");
		buffer.append(broj);
		buffer.append("\n\t\t- postanskiBroj: ");
		buffer.append(postanskiBroj);
		buffer.append("\n\t\t- mesto: ");
		buffer.append(mesto);
		buffer.append("\n\t\t- drzava: ");
		buffer.append(drzava);
		return buffer.toString();
	}
    

}
