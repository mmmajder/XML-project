package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutor", propOrder = {"anoniman", "podaciOAutoru"})
public class TAutor {
    @XmlElement(name = "Anoniman", required = true)
    private boolean anoniman;
    @XmlElement(name = "Podaci_o_autoru", required = true)
    private Autor podaciOAutoru;

    @Override
    public String toString() {
        return "TAutor {" +
                "\n\t\tanoniman=" + anoniman +
                ",\n\t\tpodaciOAutoru=" + podaciOAutoru +
                "\n\t}";
    }
}
