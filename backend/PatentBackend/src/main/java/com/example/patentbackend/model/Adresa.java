package com.example.patentbackend.model;

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
@XmlType(name = "Adresa", propOrder = {"ulica", "brojUUlici", "postanskiBroj", "mesto", "drzava"})
public class Adresa {

    @XmlElement(name = "Ulica", required = true)
    private String ulica;
    @XmlElement(name = "Broj_u_ulici", required = true)
    private int brojUUlici;
    @XmlElement(name = "Postanski_broj", required = true)
    private int postanskiBroj;
    @XmlElement(name = "Mesto", required = true)
    private String mesto;
    @XmlElement(name = "Drzava", required = true)
    private String drzava;


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n\t- ulica: ");
        buffer.append(ulica);
        buffer.append("\n\t- brojUUlici: ");
        buffer.append(brojUUlici);
        buffer.append("\n\t- postanskiBroj: ");
        buffer.append(postanskiBroj);
        buffer.append("\n\t- mesto: ");
        buffer.append(mesto);
        buffer.append("\n\t- drzava: ");
        buffer.append(drzava);
        return buffer.toString();
    }


}
