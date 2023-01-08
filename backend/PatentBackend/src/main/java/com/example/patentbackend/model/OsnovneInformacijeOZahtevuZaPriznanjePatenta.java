package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Osnovne_informacije_o_zahtevu_za_priznanje_patenta")
@XmlType(name = "", propOrder = {"brojPrijave", "datumPrijema", "priznatiDatumPodnosenja", "stanje"})
public class OsnovneInformacijeOZahtevuZaPriznanjePatenta {
    @XmlElement(name = "Broj_prijave", required = true)
    private String brojPrijave;

    @XmlElement(name = "Datum_prijema", required = true)
    @XmlSchemaType(name = "date")
    private Date datumPrijema;

    @XmlElement(name = "Priznati_datum_podnosenja", required = false)
    @XmlSchemaType(name = "date")
    private Date priznatiDatumPodnosenja;

    @XmlElement(name = "Stanje", required = true)
    private String stanje;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("- brojPrijave: ");
        buffer.append(brojPrijave);
        buffer.append("\n- datumPrijema: ");
        buffer.append(datumPrijema);
        buffer.append("\n- priznatiDatumPodnosenja: ");
        buffer.append(priznatiDatumPodnosenja);
        buffer.append("\n");
        return buffer.toString();
    }

}
