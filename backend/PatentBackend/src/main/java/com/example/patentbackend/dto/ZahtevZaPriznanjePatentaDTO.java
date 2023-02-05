package com.example.patentbackend.dto;

import com.example.patentbackend.model.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import com.example.patentbackend.model.SadrzajZahtevaZaPriznanjePatenta;
import lombok.*;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtevZaPriznanjePatentaDTO {
    private OsnovneInformacijeOZahtevuZaPriznanjePatentaDTO osnovneInformacijeOZahtevuZaPriznanjePatenta;
    private SadrzajZahtevaZaPriznanjePatentaDTO sadrzajZahtevaZaPriznanjePatenta;
    private String putanjaDoPrilogaPodnosioca;
    private String putanjaDoPrimera;
}
