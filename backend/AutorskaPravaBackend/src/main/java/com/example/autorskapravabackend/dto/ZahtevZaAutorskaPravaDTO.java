package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.AutorskoDelo;
import com.example.autorskapravabackend.model.OsnovniLicniPodaci;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZahtevZaAutorskaPravaDTO {
    private PodnosilacDTO podnosilacZahteva;
    private OsnovniLicniPodaci podaciOPunomocniku;
    private AutorDTO autor;
    private AutorskoDelo autorskoDelo;
}
