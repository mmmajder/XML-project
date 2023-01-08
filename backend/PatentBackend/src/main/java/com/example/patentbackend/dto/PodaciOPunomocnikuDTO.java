package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PodaciOPunomocnikuDTO {
    private String vrstaPunomocnika;
    private Boolean zajednickiPredstavnik;
    private TLiceDTO punomocnik;
}
