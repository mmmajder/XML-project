package com.example.autorskapravabackend.model;

import lombok.*;

import javax.xml.bind.annotation.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Autor", propOrder = {"anoniman", "podaciOAutoru"})
public class Autor {

    @XmlElement(name = "Anoniman", required = true)
    private boolean anoniman;
    @XmlElement(name = "Podaci_o_autoru", required = true)
    private PodaciOAutoru podaciOAutoru;

    @Override
    public String toString() {
        return "Autor {" +
                "\n\t\tanoniman=" + anoniman +
                ",\n\t\tpodaciOAutoru=" + podaciOAutoru +
                "\n\t}";
    }
}
