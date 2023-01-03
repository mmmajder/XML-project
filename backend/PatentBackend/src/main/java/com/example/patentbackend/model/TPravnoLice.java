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
@XmlType(name = "TPravno_Lice", propOrder = {"poslovnoIme"})
public class TPravnoLice extends TLice {
    @XmlElement(name = "Poslovno_ime", required = true)
    private String poslovnoIme;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\t- poslovnoIme: ");
        buffer.append(poslovnoIme);
        buffer.append(super.toString());
        return buffer.toString();
    }

}
