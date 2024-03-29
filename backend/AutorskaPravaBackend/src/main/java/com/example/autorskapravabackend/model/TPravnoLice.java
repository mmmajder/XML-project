package com.example.autorskapravabackend.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TPravno_lice")
@XmlType(name = "TPravno_lice", propOrder = {"poslovnoIme", "sedisteNosiocaAutorskogPrava"})
public class TPravnoLice extends TLice {

    @XmlElement(name = "Poslovno_ime", required = true)
    private String poslovnoIme;
    @XmlElement(name = "Sediste_nosioca_autorskog_prava", required = true)
    private Adresa sedisteNosiocaAutorskogPrava;

    @Override
    public String toString() {
        return "TPravnoLice {" +
                "\n\tposlovnoIme='" + poslovnoIme + '\'' +
                ",\n\tsedisteNosiocaAutorskogPrava=" + sedisteNosiocaAutorskogPrava +
                "\n}";
    }
}
