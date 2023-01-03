package com.example.patentbackend.dto;

import com.example.patentbackend.model.OsnovneInformacijeOZahtevuZaPriznanjePatenta;
import com.example.patentbackend.model.SadrzajZahtevaZaPriznanjePatenta;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtevZaPriznanjePatentaDTO {
    private OsnovneInformacijeOZahtevuZaPriznanjePatentaDTO osnovneInformacijeOZahtevuZaPriznanjePatenta;
    private SadrzajZahtevaZaPriznanjePatentaDTO sadrzajZahtevaZaPriznanjePatenta;
}
