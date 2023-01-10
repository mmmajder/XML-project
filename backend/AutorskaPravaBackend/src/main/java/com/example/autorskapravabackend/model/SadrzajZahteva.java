package com.example.autorskapravabackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Sadrzaj_zahteva")
@XmlType(name = "Sadrzaj_zahteva", propOrder = {"podnosilacZahteva", "autori", "podaciOPunomocniku", "autorskoDelo"})
public class SadrzajZahteva {

    @XmlElement(name = "Podnosilac_zahteva", required = true)
    private PodnosilacZahteva podnosilacZahteva;
    @XmlElement(name = "Podaci_o_punomocniku", required = true)
    private OsnovniLicniPodaci podaciOPunomocniku;
    @XmlElement(name = "Autori", required = true)
    private ArrayList<TAutor> autori;
    @XmlElement(name = "Autorsko_delo", required = true)
    private AutorskoDelo autorskoDelo;

    @Override
    public String toString() {
        return "SadrzajZahteva {" +
                "\n\tpodnosilacZahteva=" + podnosilacZahteva +
                ",\n\tpodaciOPunomocniku=" + podaciOPunomocniku +
                ",\n\tautori=" + autori +
                ",\n\tautorskoDelo=" + autorskoDelo +
                "\n}";
    }
}
