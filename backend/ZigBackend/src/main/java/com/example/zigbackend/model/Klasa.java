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
@XmlType(name="Klasa", propOrder={"idKlase", "punNazivKlase"})
public class Klasa {
    @XmlElement(name="Id_klase", required=true)
    private String idKlase;
    @XmlElement(name="Pun_naziv_klase", required=true)
    private String punNazivKlase;

    public Klasa(String idKlase, String punNazivKlase){
    	this.idKlase = idKlase;
    	this.punNazivKlase = punNazivKlase;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- idKlase: ");
		buffer.append(idKlase);
		buffer.append("\n\t- punNazivKlase: ");
		buffer.append(punNazivKlase);
		return buffer.toString();
	}


}
