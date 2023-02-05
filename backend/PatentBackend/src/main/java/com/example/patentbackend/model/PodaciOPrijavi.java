package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"vrstaPrijave", "brojOsnovnePrijave", "datumPodnosenjaPrijave"})
public class PodaciOPrijavi {

    @XmlElement(name = "Vrsta_prijave", required = true)
    private String vrstaPrijave;
    @XmlElement(name = "Broj_osnovne_prijave", required = true)
    private String brojOsnovnePrijave;

    @XmlElement(name = "Datum_podnosenja_prijave", required = true)
    @XmlSchemaType(name = "date")
    private Date datumPodnosenjaPrijave;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- vrstaPrijave: ");
        buffer.append(vrstaPrijave);
        buffer.append("\n- brojOsnovnePrijave: ");
        buffer.append(brojOsnovnePrijave);
        buffer.append("\n- datumPodnosenjaPrijave: ");
        buffer.append(datumPodnosenjaPrijave);
        return buffer.toString();
    }
}
