package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.Autor;
import com.example.autorskapravabackend.model.AutorskoDelo;
import com.example.autorskapravabackend.model.OsnovniLicniPodaci;
import com.example.autorskapravabackend.model.PodnosilacZahteva;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZahtevZaAutorskaPravaDTO {
    private PodnosilacZahteva podnosilacZahteva;
    private OsnovniLicniPodaci podaciOPunomocniku;
    private Autor autor;
    private AutorskoDelo autorskoDelo;
}
