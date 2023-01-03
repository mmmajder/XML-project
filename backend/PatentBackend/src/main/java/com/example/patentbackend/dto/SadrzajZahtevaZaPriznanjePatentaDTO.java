package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SadrzajZahtevaZaPriznanjePatentaDTO {
    private NazivPronalaskaDTO nazivPronalaska;
    private PodaciOPodnosiocuPrijaveDTO podaciOPodnosiocuPrijave;
    private PodaciOPronalazacuDTO podaciOPronalazacu;
    private PodaciOPunomocnikuDTO podaciOPunomocniku;
    private DostavljanjeDTO dostavljanje;
    private PodaciOPrijaviDTO podaciOPrijavi;
    private List<RanijaPrijavaDTO> zahtevZaPriznanjePrvenstvaIzRanijihPrijava;

}
