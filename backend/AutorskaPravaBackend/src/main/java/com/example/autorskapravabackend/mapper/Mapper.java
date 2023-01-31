package com.example.autorskapravabackend.mapper;

import com.example.autorskapravabackend.dto.*;
import com.example.autorskapravabackend.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static List<SimpleZahtevDTO> mapToSimpleZahtevs(List<ZahtevZaAutorskaPrava> zahtevs) {
        List<SimpleZahtevDTO> simpleZahtevDTOs = new ArrayList<>();

        for (ZahtevZaAutorskaPrava zahtev : zahtevs) {
            SimpleZahtevDTO simpleZahtevDTO = mapToSimpleZahtev(zahtev);
            simpleZahtevDTOs.add(simpleZahtevDTO);
        }

        return simpleZahtevDTOs;
    }

    public static SimpleZahtevDTO mapToSimpleZahtev(ZahtevZaAutorskaPrava zahtev) {
        SimpleZahtevDTO simpleZahtevDTO = new SimpleZahtevDTO();
        simpleZahtevDTO.setBrojPrijave(zahtev.getInformacijeOZahtevu().getBrojPrijave());

        String datumPodnosenja = zahtev.getInformacijeOZahtevu().getDatumPodnosenja().toString();
        simpleZahtevDTO.setDatumPodnosenja(datumPodnosenja);

        SimpleUserDTO podnosioc = mapToSimpleUser(zahtev.getSadrzajZahteva().getPodnosilacZahteva());
        simpleZahtevDTO.setPodnosioc(podnosioc);

        simpleZahtevDTO.setObradjen(zahtev.getStatus() != EStatus.PREDATO);

        return simpleZahtevDTO;
    }

    public static SimpleUserDTO mapToSimpleUser(PodnosilacZahteva podnosilacZahteva) {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setEmail(podnosilacZahteva.getPodaciOPodnosiocu().getEmail());

        if (podnosilacZahteva.getPodaciOPodnosiocu() instanceof TFizickoLice) {
            simpleUserDTO.setName(((TFizickoLice) podnosilacZahteva.getPodaciOPodnosiocu()).getOsnovniLicniPodaci().getIme() +
                    " " + ((TFizickoLice) podnosilacZahteva.getPodaciOPodnosiocu()).getOsnovniLicniPodaci().getPrezime());
        } else {
            simpleUserDTO.setName(((TPravnoLice) podnosilacZahteva.getPodaciOPodnosiocu()).getPoslovnoIme());
        }

        return simpleUserDTO;
    }

    public static String mapDateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");

        return dateFormat.format(date);
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
