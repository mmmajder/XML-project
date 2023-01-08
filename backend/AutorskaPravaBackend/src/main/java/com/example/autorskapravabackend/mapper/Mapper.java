package com.example.autorskapravabackend.mapper;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;

public class Mapper {

    public static ZahtevZaAutorskaPrava dtoToZahtev(ZahtevZaAutorskaPravaDTO dto) {
        return new ZahtevZaAutorskaPrava();
    }
}
