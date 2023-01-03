package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TLiceDTO {
    private AdresaDTO adresaLica;
    private String brojTelefona;
    private String brojFaksa;
    private String email;
    private String ime;
    private String prezime;
    private String drzavljanstvo;
    private String poslovnoIme;

}
