package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TFizicko_Lice", propOrder={"ime", "prezime"})
public class FizickoLice extends Lice {
    @XmlElement(name="Ime", required=true)
    private String ime;
    @XmlElement(name="Prezime", required=true)
    private String prezime;
	
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


