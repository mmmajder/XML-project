package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(
        name = "Zahtev_za_unosenje_u_evidenciju_i_deponovanje_autorskih_dela"
)
@XmlType(
        name = "",
        propOrder = {"informacijeOZahtevu", "sadrzajZahteva"}
)
public class ZahtevZaAutorskaPrava {
    @XmlElement(
            name = "Informacije_o_zahtevu",
            required = true
    )
    private InformacijeOZahtevu informacijeOZahtevu;
    @XmlElement(
            name = "Sadrzaj_zahteva",
            required = true
    )
    private SadrzajZahteva sadrzajZahteva;

    @Override
    public String toString() {
        return "ZahtevZaUnosenjeUEvidencijuIDeponovanjeAutorskihDela {" +
                "\ninformacijeOZahtevu=" + informacijeOZahtevu +
                ",\nsadrzajZahteva=" + sadrzajZahteva +
                "\n}";
    }
}
