package com.example.autorskapravabackend.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Autor", propOrder = {"pseudonim", "autor"})
public class Autor {
    @XmlElement(name = "Pseudonim")
    private String pseudonim;
    @XmlElement(name = "Autor")
    private TLice autor;

    @Override
    public String toString() {
        return "Autor {" +
                "\n\t\tpseudonim='" + pseudonim + '\'' +
                ",\n\t\tautor=" + autor +
                "\n\t}";
    }
}
