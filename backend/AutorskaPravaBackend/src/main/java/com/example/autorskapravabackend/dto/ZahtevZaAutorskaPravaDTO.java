package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.AutorskoDelo;
import com.example.autorskapravabackend.model.OsnovniLicniPodaci;
import com.example.autorskapravabackend.model.PodnosilacZahteva;
import com.example.autorskapravabackend.model.TAutor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ZahtevZaAutorskaPravaDTO {
    private PodnosilacZahteva podnosilacZahteva;
    private OsnovniLicniPodaci podaciOPunomocniku;
    private ArrayList<TAutor> autori;
    private AutorskoDelo autorskoDelo;
}
