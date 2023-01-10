package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Podnosilac_zahteva", propOrder = {"podnosilacJeIAutor", "podaciOPodnosiocu"})
public class PodnosilacZahteva {

    @XmlElement(name = "Podnosilac_je_i_autor", required = true)
    private boolean podnosilacJeIAutor;
    @XmlElement(name = "Podaci_o_podnosiocu")
    private TLice podaciOPodnosiocu;

    @Override
    public String toString() {
        return "PodnosilacZahteva {" +
                "\n\t\tpodnosilacJeIAutor=" + podnosilacJeIAutor +
                ",\n\t\tpodaciOPodnosiocu=" + podaciOPodnosiocu +
                "\n\t}";
    }
}
