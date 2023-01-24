package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Zig", propOrder={"tipZiga", "opisIzgledaZiga", "drugaVrstaZnakaOpis", "izgledPutanjaDoSlike", "boja",
        "transliteracijaZnaka", "prevodZnaka", "opisZnaka"})
public class Zig {
    //TODO da li ovde ostaviti String ili Enum tipove?
    @XmlElement(name="Tip_ziga", required=true)
    private ETip_ziga tipZiga;
    @XmlElement(name="Opis_izgleda_ziga", required=true)
    private EOpis_izgleda_ziga opisIzgledaZiga;
    @XmlElement(name="Druga_vrsta_znaka_opis", required=false) // required only if opisIzgledaZiga is DRUGA_VRSTA_ZNAKA
    private String drugaVrstaZnakaOpis;
    @XmlElement(name="Izgled_putanja_do_slike", required=true)
    private String izgledPutanjaDoSlike;
    @XmlElement(name="Boja", required=true)
    private List<EBoja> boja;
    @XmlElement(name="Transliteracija_znaka", required=true)
    private String transliteracijaZnaka;
    @XmlElement(name="Prevod_znaka", required=true)
    private String prevodZnaka;
    @XmlElement(name="Opis_znaka", required=true)
    private String opisZnaka;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- tipZiga: ");
		buffer.append(tipZiga);
		buffer.append("\n\t- opisIzgledaZiga: ");
		buffer.append(opisIzgledaZiga);
		buffer.append("\n\t- drugaVrstaZnakaOpis: ");
		buffer.append(drugaVrstaZnakaOpis);
		buffer.append("\n\t- izgledPutanjaDoSlike: ");
		buffer.append(izgledPutanjaDoSlike);
		buffer.append("\n\t- boje: ");
		
		for (EBoja posebna_boja : boja) {
			buffer.append("\n\t\t- " + posebna_boja);
		}
		
		buffer.append("\n\t- transliteracijaZnaka: ");
		buffer.append(transliteracijaZnaka);
		buffer.append("\n\t- prevodZnaka: ");
		buffer.append(prevodZnaka);
		buffer.append("\n\t- opisZnaka: ");
		buffer.append(opisZnaka);
		return buffer.toString();
	}
}
