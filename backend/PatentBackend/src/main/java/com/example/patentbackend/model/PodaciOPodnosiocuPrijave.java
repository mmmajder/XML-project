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
@XmlType(name = "", propOrder = {"podnosilacPrijaveJeIPronalazac", "podnosilacPrijave"})
public class PodaciOPodnosiocuPrijave {
    @XmlElement(name = "Podnosilac_prijave_je_i_pronalazac", required = true)
    private Boolean podnosilacPrijaveJeIPronalazac;

    @XmlElement(name = "Podnosilac_prijave", required = true)
    private TLice podnosilacPrijave;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- podnosilacPrijaveJeIPronalazac: ");
        buffer.append(podnosilacPrijaveJeIPronalazac);
        buffer.append("\n- podnosilacPrijave: ");
        buffer.append(podnosilacPrijave);
        return buffer.toString();
    }
}
