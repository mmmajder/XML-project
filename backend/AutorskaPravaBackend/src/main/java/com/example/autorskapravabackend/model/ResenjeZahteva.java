package com.example.autorskapravabackend.model;

import com.example.autorskapravabackend.dto.SimpleUser;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.Date;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "brojPrijave",
        "sluzbenik",
        "datumObrade",
        "odbijen",
        "razlogOdbijanja",
        "sifra"
})
@XmlRootElement(name = "resenje_zahteva")
public class ResenjeZahteva {
    @XmlElement(name = "Broj_prijave", required = true)
    private String brojPrijave;
    @XmlElement(name = "Sluzbenik")
    private SimpleUser sluzbenik;
    @XmlElement(name = "Datum_obrade")
    @XmlSchemaType(name = "date")
    private Date datumObrade;
    @XmlElement(name = "Odbijen")
    private boolean odbijen;
    @XmlElement(name = "Razlog_odbijanja")
    private String razlogOdbijanja;
    @XmlElement(name = "Sifra")
    private String sifra;
}
