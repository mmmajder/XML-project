package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Osnovni_licni_podaci")
@XmlType(name = "Osnovni_licni_podaci", propOrder = {"adresa", "ime", "prezime"})
public class OsnovniLicniPodaci {

    @XmlElement(name = "Adresa", required = true)
    private Adresa adresa;
    @XmlElement(name = "Ime", required = true)
    private String ime;
    @XmlElement(name = "Prezime", required = true)
    private String prezime;

    @Override
    public String toString() {
        return "OsnovniLicniPodaci {" +
                "\n\t\tadresa=" + adresa +
                ",\n\t\time='" + ime + '\'' +
                ",\n\t\tprezime='" + prezime + '\'' +
                "\n\t}";
    }
}
