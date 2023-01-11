package com.example.autorskapravabackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Informacije_o_zahtevu")
@XmlType(name = "Informacije_o_zahtevu", propOrder = {"brojPrijave", "datumPodnosenja", "listaPrilogaKojiSePodnoseUzZahtev"})
public class InformacijeOZahtevu {
    @XmlElement(name = "Broj_prijave", required = true)
    private String brojPrijave;
    @XmlElement(name = "Datum_podnosenja", required = true)
    @XmlSchemaType(name = "date")
    private Date datumPodnosenja;
    @XmlElement(name = "Lista_priloga_koji_se_podnose_uz_zahtev")
    @XmlSchemaType(name = "list")
    private ArrayList<String> listaPrilogaKojiSePodnoseUzZahtev;

    @Override
    public String toString() {
        return "OsnovneInformacijeOZahtevu {" +
                "\n\tbrojPrijave='" + brojPrijave + '\'' +
                ",\n\tdatumPodnosenja=" + datumPodnosenja +
                ",\n\tlistaPrilogaKojiSePodnoseUzZahtev=" + listaPrilogaKojiSePodnoseUzZahtev +
                "\n}";
    }
}
