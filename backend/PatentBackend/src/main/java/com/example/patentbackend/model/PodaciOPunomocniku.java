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
@XmlType(name = "", propOrder = {"vrstaPunomocnika", "zajednickiPredstavnik", "punomocnik"})
public class PodaciOPunomocniku {

    @XmlElement(name = "Vrsta_punomocnika", required = true)
    private String vrstaPunomocnika;

    @XmlElement(name = "Zajednicki_predstavnik", required = true)
    private Boolean zajednickiPredstavnik;

    @XmlElement(name = "Punomocnik", required = true)
    private TLice punomocnik;

    public String getVrstaPunomocnika() {
        return vrstaPunomocnika;
    }

    public void setVrstaPunomocnika(String vrstaPunomocnika) {
        this.vrstaPunomocnika = vrstaPunomocnika;
    }

    public Boolean getZajednickiPredstavnik() {
        return zajednickiPredstavnik;
    }

    public void setZajednickiPredstavnik(Boolean zajednickiPredstavnik) {
        this.zajednickiPredstavnik = zajednickiPredstavnik;
    }

    public TLice getPunomocnik() {
        return punomocnik;
    }

    public void setPunomocnik(TLice punomocnik) {
        this.punomocnik = punomocnik;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- vrstaPunomocnika: ");
        buffer.append(vrstaPunomocnika);
        buffer.append("\n- zajednickiPredstavnik: ");
        buffer.append(zajednickiPredstavnik);
        buffer.append("\n- punomocnik: ");
        buffer.append(punomocnik);
        return buffer.toString();
    }
}
