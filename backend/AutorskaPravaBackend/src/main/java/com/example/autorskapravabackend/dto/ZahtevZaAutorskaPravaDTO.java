package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.AutorskoDelo;
import com.example.autorskapravabackend.model.OsnovniLicniPodaci;
import com.example.autorskapravabackend.model.PodnosilacZahteva;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZahtevZaAutorskaPravaDTO {
    private PodnosilacZahteva podnosilacZahteva;
    private OsnovniLicniPodaci podaciOPunomocniku;
    private List<TAutorDTO> autori;
    private AutorskoDelo autorskoDelo;
}
