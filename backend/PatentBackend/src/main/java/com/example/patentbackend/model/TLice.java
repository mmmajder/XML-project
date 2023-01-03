package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"adresaLica", "brojTelefona", "brojFaksa", "ePosta"})
public class TLice {

    @XmlElement(name = "Adresa_lica", required = true)
    private Adresa adresaLica;
    @XmlElement(name = "Broj_telefona", required = true)
    private String brojTelefona;
    @XmlElement(name = "Broj_faksa", required = true)
    private String brojFaksa;
    @XmlElement(name = "E_posta", required = true)
    private String ePosta;

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n- adresaLica: ");
        buffer.append(adresaLica);
        buffer.append("\n- brojTelefona: ");
        buffer.append(brojTelefona);
        buffer.append("\n- brojFaksa: ");
        buffer.append(brojFaksa);
        buffer.append("\n- ePosta: ");
        buffer.append(ePosta);
        return buffer.toString();
    }

}
