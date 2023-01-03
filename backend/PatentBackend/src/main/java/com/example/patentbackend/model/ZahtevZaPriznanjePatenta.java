package com.example.patentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zahtev_za_priznanje_patenta")
@XmlType(name = "", propOrder = {"osnovneInformacijeOZahtevuZaPriznanjePatenta", "sadrzajZahtevaZaPriznanjePatenta"})
public class ZahtevZaPriznanjePatenta {

    @XmlElement(name = "Osnovne_informacije_o_zahtevu_za_priznanje_patenta", required = true)
    private OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta;

    @XmlElement(name = "Sadrzaj_zahteva_za_priznanje_patenta", required = true)
    private SadrzajZahtevaZaPriznanjePatenta sadrzajZahtevaZaPriznanjePatenta;

    public OsnovneInformacijeOZahtevuZaPriznanjePatenta getOsnovneInformacijeOZahtevuZaPriznanjePatenta() {
        return osnovneInformacijeOZahtevuZaPriznanjePatenta;
    }

    public void setOsnovneInformacijeOZahtevuZaPriznanjePatenta(
            OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta) {
        this.osnovneInformacijeOZahtevuZaPriznanjePatenta = osnovneInformacijeOZahtevuZaPriznanjePatenta;
    }

    public SadrzajZahtevaZaPriznanjePatenta getSadrzajZahtevaZaPriznanjePatenta() {
        return sadrzajZahtevaZaPriznanjePatenta;
    }

    public void setSadrzajZahtevaZaPriznanjePatenta(SadrzajZahtevaZaPriznanjePatenta sadrzajZahtevaZaPriznanjePatenta) {
        this.sadrzajZahtevaZaPriznanjePatenta = sadrzajZahtevaZaPriznanjePatenta;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("- OsnovneInformacijeOZahtevuZaPriznanjePatenta: \n");
        buffer.append(osnovneInformacijeOZahtevuZaPriznanjePatenta);
        buffer.append("- SadrzajZahtevaZaPriznanjePatenta: \n");
        buffer.append(sadrzajZahtevaZaPriznanjePatenta);
        buffer.append("\n");
        return buffer.toString();
    }

}
