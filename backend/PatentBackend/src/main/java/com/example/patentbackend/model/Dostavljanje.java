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
@XmlType(name = "", propOrder = {"adresaZaDostavljanje", "nacinDostave"})
public class Dostavljanje {
    @XmlElement(name = "Adresa_za_dostavljanje", required = true)
    private Adresa adresaZaDostavljanje;

    @XmlElement(name = "Nacin_dostave", required = true)
    private String nacinDostave;

    public Adresa getAdresaZaDostavljanje() {
        return adresaZaDostavljanje;
    }

    public void setAdresaZaDostavljanje(Adresa adresaZaDostavljanje) {
        this.adresaZaDostavljanje = adresaZaDostavljanje;
    }

    public String getNacinDostave() {
        return nacinDostave;
    }

    public void setNacinDostave(String nacinDostave) {
        this.nacinDostave = nacinDostave;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- adresaZaDostavljanje: ");
        buffer.append(adresaZaDostavljanje);
        buffer.append("\n- nacinDostave: ");
        buffer.append(nacinDostave);
        return buffer.toString();
    }
}
