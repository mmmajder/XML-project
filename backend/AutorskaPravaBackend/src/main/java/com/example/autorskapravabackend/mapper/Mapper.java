package com.example.autorskapravabackend.mapper;

import com.example.autorskapravabackend.dto.TAutorDTO;
import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.model.SadrzajZahteva;
import com.example.autorskapravabackend.model.TAutor;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static ZahtevZaAutorskaPrava dtoToZahtev(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava z = new ZahtevZaAutorskaPrava();
        SadrzajZahteva sadrzaj = new SadrzajZahteva();
        sadrzaj.setAutori(autorDTOtoAutori(dto.getAutori()));
        sadrzaj.setAutorskoDelo(dto.getAutorskoDelo());
        sadrzaj.setPodnosilacZahteva(dto.getPodnosilacZahteva());
        sadrzaj.setPodaciOPunomocniku(dto.getPodaciOPunomocniku());
        z.setSadrzajZahteva(sadrzaj);
        return z;
    }

    private static ArrayList<TAutor> autorDTOtoAutori(List<TAutorDTO> dtos) {
        ArrayList<TAutor> autori = new ArrayList<>();
        for (TAutorDTO a : dtos)
            autori.add(TAutor.builder().podaciOAutoru(a.getPodaciOAutoru()).build());
        return autori;
    }
}
