package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdresaDTO {
    private String ulica;
    private int brojUUlici;
    private int postanskiBroj;
    private String mesto;
    private String drzava;
}
