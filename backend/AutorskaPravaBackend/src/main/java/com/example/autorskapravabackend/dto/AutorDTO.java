package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.Adresa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutorDTO {
    private boolean anoniman;
    private String pseudonim;
    private int godinaSmrti;
    private String drzavljanstvo;
    private String ime;
    private String prezime;
    private Adresa adresa;
}
