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
@XmlType(name = "TFizicko_Lice", propOrder = {"ime", "prezime", "drzavljanstvo"})
public class TFizickoLice extends TLice {
    @XmlElement(name = "Ime", required = true)
    private String ime;
    @XmlElement(name = "Prezime", required = true)
    private String prezime;
    @XmlElement(name = "Drzavljanstvo", required = true)
    private String drzavljanstvo;

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
