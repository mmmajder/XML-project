package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFizicko_Lice", propOrder = {"drzavljanstvo", "osnovniLicniPodaci", "godinaSmrti"})
public class TFizickoLice extends TLice {

    @XmlElement(name = "Drzavljanstvo", required = true)
    private String drzavljanstvo;

    @XmlElement(name = "Osnovni_licni_podaci", required = true)
    private OsnovniLicniPodaci osnovniLicniPodaci;

    @XmlElement(name = "Godina_smrti")
    private int godinaSmrti;

    @Override
    public String toString() {
        return "TFizickoLice {" +
                "\n\t\t\tdrzavljanstvo='" + drzavljanstvo + '\'' +
                ",\n\t\t\tosnovniLicniPodaci=" + osnovniLicniPodaci +
                ",\n\t\t\tgodinaSmrti=" + godinaSmrti +
                "\n\t\t}";
    }
}