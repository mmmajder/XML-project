package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class NazivPronalaska {
    @XmlAttribute(name = "Jezik", required = true)
    private String jezik;

    @XmlAttribute(name = "Naziv", required = true)
    private String naziv;

    @Override
    public String toString() {
        return "NazivPronalaska [jezik=" + jezik + ", naziv=" + naziv + "]";
    }
}
