package com.example.zigbackend.dto;

import com.example.zigbackend.model.Adresa;
import com.example.zigbackend.model.Kontakt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LiceDTO {
    public String ime;
    public String prezime;
    public String poslovnoIme;
    public Adresa adresa;
    public Kontakt kontakt;
    public String tipLica;
}