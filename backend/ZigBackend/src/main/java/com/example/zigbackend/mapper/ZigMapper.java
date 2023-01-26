package com.example.zigbackend.mapper;

import com.example.zigbackend.dto.ZahtevZaPriznanjeZigaDTO;
import com.example.zigbackend.model.*;
import com.example.zigbackend.repository.ZigRepository;

import java.util.Date;
import java.util.List;

public class ZigMapper {

    public static ZahtevZaPriznanjeZiga createZahtev(ZahtevZaPriznanjeZigaDTO zahtevZaPriznanjeZigaDTO){
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = copyFromDTO(zahtevZaPriznanjeZigaDTO);
        Date now = new Date(System.currentTimeMillis());
        zahtevZaPriznanjeZiga.setDatumPodnosenja(now);
        zahtevZaPriznanjeZiga.setStatus(EStatus.PREDATO);
        // brojPrijave has to be set in service

        return zahtevZaPriznanjeZiga;
    }

    private static ZahtevZaPriznanjeZiga copyFromDTO(ZahtevZaPriznanjeZigaDTO zahtevZaPriznanjeZigaDTO){
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = new ZahtevZaPriznanjeZiga();
        zahtevZaPriznanjeZiga.setPodnosilacPrijave(zahtevZaPriznanjeZigaDTO.getPodnosilacPrijave());
        zahtevZaPriznanjeZiga.setPunomocnik(zahtevZaPriznanjeZigaDTO.getPunomocnik());
        zahtevZaPriznanjeZiga.setPredstavnik(zahtevZaPriznanjeZigaDTO.getPredstavnik());
        zahtevZaPriznanjeZiga.setZig(zahtevZaPriznanjeZigaDTO.getZig());
        zahtevZaPriznanjeZiga.setKlasa(zahtevZaPriznanjeZigaDTO.getKlasa());
        zahtevZaPriznanjeZiga.setZatrazenoPravoPrvenstvaIOsnov(zahtevZaPriznanjeZigaDTO.getZatrazenoPravoPrvenstvaIOsnov());
        zahtevZaPriznanjeZiga.setPrilog(zahtevZaPriznanjeZigaDTO.getPrilog());
        zahtevZaPriznanjeZiga.setPrilogPunomocje(zahtevZaPriznanjeZigaDTO.getPrilogPunomocje());

        return zahtevZaPriznanjeZiga;
    }



}
