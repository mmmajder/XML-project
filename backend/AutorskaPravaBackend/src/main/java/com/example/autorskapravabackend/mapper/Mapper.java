package com.example.autorskapravabackend.mapper;

import com.example.autorskapravabackend.dto.AutorDTO;
import com.example.autorskapravabackend.dto.PodnosilacDTO;
import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.model.*;

public class Mapper {

    public static ZahtevZaAutorskaPrava dtoToZahtev(ZahtevZaAutorskaPravaDTO dto) {
        ZahtevZaAutorskaPrava z = new ZahtevZaAutorskaPrava();
        SadrzajZahteva sadrzaj = new SadrzajZahteva();
        sadrzaj.setAutor(dtoToAutor(dto.getAutor()));
        sadrzaj.setAutorskoDelo(dto.getAutorskoDelo());
        sadrzaj.setPodnosilacZahteva(dtoToPodnosilac(dto.getPodnosilacZahteva()));
        sadrzaj.setPodaciOPunomocniku(dto.getPodaciOPunomocniku());
        z.setSadrzajZahteva(sadrzaj);
        return z;
    }

    private static PodnosilacZahteva dtoToPodnosilac(PodnosilacDTO podnosilacZahteva) {
        TLice lice;
        if (podnosilacZahteva.getPoslovnoIme() != null | podnosilacZahteva.getPoslovnoIme().equals(""))
            lice = new TFizickoLice(podnosilacZahteva.getDrzavljanstvo(), OsnovniLicniPodaci.builder()
                    .ime(podnosilacZahteva.getIme())
                    .prezime(podnosilacZahteva.getPrezime())
                    .adresa(podnosilacZahteva.getAdresa())
                    .build());
        else
            lice = new TPravnoLice(podnosilacZahteva.getPoslovnoIme(), podnosilacZahteva.getSediste());

        lice.setBrojTelefona(podnosilacZahteva.getBrojTelefona());
        lice.setEmail(podnosilacZahteva.getEmail());

        return PodnosilacZahteva.builder()
                .podaciOPodnosiocu(lice)
                .build();
    }

    private static Autor dtoToAutor(AutorDTO autor) {
        return Autor.builder()
                .anoniman(autor.isAnoniman())
                .podaciOAutoru(PodaciOAutoru.builder()
                        .pseudonim(autor.getPseudonim())
                        .lice(new TFizickoLice(autor.getDrzavljanstvo(),
                                OsnovniLicniPodaci.builder()
                                        .ime(autor.getIme())
                                        .prezime(autor.getPrezime())
                                        .adresa(autor.getAdresa())
                                        .build(),
                                autor.getGodinaSmrti()))
                        .build())
                .build();
    }
}
