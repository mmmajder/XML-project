package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.Adresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PodnosilacDTO {
    private String drzavljanstvo;
    private String ime;
    private String prezime;
    private Adresa adresa;
    private String brojTelefona;
    private String email;
    private String poslovnoIme;
    private Adresa sediste;
}
