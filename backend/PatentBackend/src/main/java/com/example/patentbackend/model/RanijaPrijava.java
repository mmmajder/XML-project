package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlSchemaType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"datumPodnosenjaPrijave", "brojRanijePrijave", "dvoslovnaOznaka"})
public class RanijaPrijava {
    @XmlElement(name = "Datum_podnosenja_priave", required = true)
    @XmlSchemaType(name = "date")
    private Date datumPodnosenjaPrijave;
    @XmlElement(name = "Broj_ranije_prijave", required = true)
    private String brojRanijePrijave;
    @XmlElement(name = "Dvoslovna_oznaka", required = true)
    private String dvoslovnaOznaka;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- Ranija prijava: ");
        buffer.append("\n- datumPodnosenjaPrijave: ");
        buffer.append(datumPodnosenjaPrijave);
        buffer.append("\n- brojRanijePrijave: ");
        buffer.append(brojRanijePrijave);
        buffer.append("\n- dvoslovnaOznaka: ");
        buffer.append(dvoslovnaOznaka);
        return buffer.toString();
    }

}
