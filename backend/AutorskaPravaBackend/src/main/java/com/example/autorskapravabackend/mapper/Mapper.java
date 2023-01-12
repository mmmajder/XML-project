package com.example.autorskapravabackend.mapper;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.model.SadrzajZahteva;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;

public class Mapper {

    public static ZahtevZaAutorskaPrava dtoToZahtev(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava z = new ZahtevZaAutorskaPrava();
        SadrzajZahteva sadrzaj = new SadrzajZahteva();
        sadrzaj.setAutor(dto.getAutor());
        sadrzaj.setAutorskoDelo(dto.getAutorskoDelo());
        sadrzaj.setPodnosilacZahteva(dto.getPodnosilacZahteva());
        sadrzaj.setPodaciOPunomocniku(dto.getPodaciOPunomocniku());
        z.setSadrzajZahteva(sadrzaj);
        return z;
    }
}
