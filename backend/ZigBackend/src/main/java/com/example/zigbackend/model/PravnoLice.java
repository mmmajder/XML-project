package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name= "TPravno_lice", propOrder={"poslovnoIme"})
public class PravnoLice extends Lice {
    @XmlElement(name="Poslovno_ime", required=true)
    private String poslovnoIme;
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\n\t- poslovnoIme: ");
		buffer.append(poslovnoIme);
		return buffer.toString();
	}
}



