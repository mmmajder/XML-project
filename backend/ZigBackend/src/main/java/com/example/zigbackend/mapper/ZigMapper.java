package com.example.zigbackend.mapper;

import com.example.zigbackend.dto.*;
import com.example.zigbackend.model.*;
import com.example.zigbackend.repository.ZigRepository;
import com.example.zigbackend.resenje.ResenjeZahteva;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        zahtevZaPriznanjeZiga.setPodnosilacPrijave(copyFromLiceDTO(zahtevZaPriznanjeZigaDTO.getPodnosilacPrijave()));
        zahtevZaPriznanjeZiga.setPunomocnik(copyFromLiceDTO(zahtevZaPriznanjeZigaDTO.getPunomocnik()));
        zahtevZaPriznanjeZiga.setPredstavnik(copyFromLiceDTO(zahtevZaPriznanjeZigaDTO.getPredstavnik()));
        zahtevZaPriznanjeZiga.setZig(getMappedZig(zahtevZaPriznanjeZigaDTO.getZigDTO()));
        zahtevZaPriznanjeZiga.setKlasa(getMappedKlasa(zahtevZaPriznanjeZigaDTO.getKlasaConcatenated()));
        zahtevZaPriznanjeZiga.setZatrazenoPravoPrvenstvaIOsnov(zahtevZaPriznanjeZigaDTO.getZatrazenoPravoPrvenstvaIOsnov());
        zahtevZaPriznanjeZiga.setPrilog(getStatusedPrilogs(zahtevZaPriznanjeZigaDTO.getNeededPrilogsConcatenated()));
        zahtevZaPriznanjeZiga.setPrilogPunomocje(getStatusedPrilogPunomocje(zahtevZaPriznanjeZigaDTO.getStatusPrilogPunomocje()));

        return zahtevZaPriznanjeZiga;
    }

    private static Lice copyFromLiceDTO(LiceDTO liceDTO){
        Lice lice;

        if ("fizickoLice".equals(liceDTO.tipLica)){
            lice = new FizickoLice();
            ((FizickoLice) lice).setIme(liceDTO.ime);
            ((FizickoLice) lice).setPrezime(liceDTO.prezime);
        } else {
            lice = new PravnoLice();
            ((PravnoLice) lice).setPoslovnoIme(liceDTO.poslovnoIme);
        }

        lice.adresa = liceDTO.adresa;
        lice.kontakt = liceDTO.kontakt;

        return lice;
    }

    private static Zig getMappedZig(ZigDTO zigDTO){
        Zig zig = new Zig();
        zig.setTipZiga(zigDTO.getTipZiga());
        zig.setOpisIzgledaZiga(zigDTO.getOpisIzgledaZiga());
        zig.setDrugaVrstaZnakaOpis(zigDTO.getDrugaVrstaZnakaOpis());
        zig.setPrevodZnaka(zigDTO.getPrevodZnaka());
        zig.setTransliteracijaZnaka(zigDTO.getTransliteracijaZnaka());
        zig.setOpisZnaka(zigDTO.getOpisZnaka());

        List<String> bojeStr = Arrays.asList(zigDTO.getBojaConcatenated().split("\\|"));
        List<EBoja> boje = new ArrayList<>();

        for (String bojaStr : bojeStr){
            if (!"".equals(bojaStr)){
                boje.add(EBoja.valueOf(bojaStr));
            }
        }

        zig.setBoja(boje);

        return zig;
    }

    private static List<Prilog> getStatusedPrilogs(String neededPrilogsConcatenated){
//        List<String> neededPrilogs = neededPrilogsDTO.getNeededPrilogs();
        List<String> neededPrilogs = Arrays.asList(neededPrilogsConcatenated.split("\\|"));
        List<Prilog> prilogs  = new ArrayList<>();

        for (ETip_priloga tip : ETip_priloga.values()){
            if (tip != ETip_priloga.PUNOMOCJE){
                Prilog p = new Prilog();
                p.setTipPriloga(tip);

                if (doesNeededPrilogsContainPrilogType(neededPrilogs, tip)){
                    p.setStatusPriloga(EStatus_priloga.NIJE_PREDATO);
                } else {
                    p.setStatusPriloga(EStatus_priloga.NIJE_POTREBNO);
                }

                prilogs.add(p);
            }
        }

        return prilogs;
    }

    private static boolean doesNeededPrilogsContainPrilogType(List<String> neededPrilogs, ETip_priloga prilogType){
        for (String neededPrilog : neededPrilogs){
            if (prilogType.equals(ETip_priloga.valueOf(neededPrilog))){
                return true;
            }
        }

        return false;
    }

    private static PrilogPunomocje getStatusedPrilogPunomocje(String status){
        PrilogPunomocje prilogPunomocje  = new PrilogPunomocje();
        prilogPunomocje.setStatusPriloga(EStatus_prilog_punomocje.valueOf(status));

        return prilogPunomocje;
    }

    private static List<Klasa> getMappedKlasa(String klasaConcatenated){
        List<String> klaseStr = Arrays.asList(klasaConcatenated.split("\\|"));
        List<Klasa> klase  = new ArrayList<>();

        for (String klasaStr : klaseStr){
            if (!"".equals(klasaStr)){
                String[] klasaParts = klasaStr.split(" - ");
                String id = klasaParts[0];
                String naziv = klasaParts[1];
                Klasa klasa = new Klasa();
                klasa.setIdKlase(id);
                klasa.setPunNazivKlase(naziv);
                klase.add(klasa);
            }
        }

        return klase;
    }

    public static List<SimpleZahtevDTO> mapToSimpleZahtevs(List<ZahtevZaPriznanjeZiga> zahtevZaPriznanjeZigas){
        List<SimpleZahtevDTO> simpleZahtevDTOs = new ArrayList<>();

        for (ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga : zahtevZaPriznanjeZigas){
            SimpleZahtevDTO simpleZahtevDTO = mapToSimpleZahtev(zahtevZaPriznanjeZiga);
            simpleZahtevDTOs.add(simpleZahtevDTO);
        }

        return simpleZahtevDTOs;
    }

    public static SimpleZahtevDTO mapToSimpleZahtev(ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga){
        SimpleZahtevDTO simpleZahtevDTO = new SimpleZahtevDTO();
        simpleZahtevDTO.setBrojPrijave(zahtevZaPriznanjeZiga.getBrojPrijaveZiga());

        Date datumPodnosenja = zahtevZaPriznanjeZiga.getDatumPodnosenja();
        simpleZahtevDTO.setDatumPodnosenja(mapDateToString(datumPodnosenja));

        SimpleUserDTO podnosioc = mapToSimpleUser(zahtevZaPriznanjeZiga.getPodnosilacPrijave());
        simpleZahtevDTO.setPodnosioc(podnosioc);

        simpleZahtevDTO.setObradjen(zahtevZaPriznanjeZiga.getStatus() != EStatus.PREDATO);

        return simpleZahtevDTO;
    }

    public static SimpleUserDTO mapToSimpleUser(Lice user){
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setEmail(user.getKontakt().getEmail());

        if (user instanceof FizickoLice){
            simpleUserDTO.setName(((FizickoLice) user).getIme() + " " + ((FizickoLice) user).getPrezime());
        } else {
            simpleUserDTO.setName(((PravnoLice) user).getPoslovnoIme());
        }

        return simpleUserDTO;
    }

    public static String mapDateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");

        return dateFormat.format(date);
    }

    public static ObradaZahteva mapToObradaZahteva(ResenjeZahteva resenjeZahteva, SimpleUserDTO simpleUserDTO){
        ObradaZahteva obradaZahteva = new ObradaZahteva();
        obradaZahteva.setSluzbenik(simpleUserDTO);
        obradaZahteva.setDatumObrade(mapDateToString(resenjeZahteva.getDatumObrade()));
        obradaZahteva.setRazlogOdbijanja(resenjeZahteva.getRazlogOdbijanja());
        obradaZahteva.setOdbijen(resenjeZahteva.isOdbijen());
        obradaZahteva.setSifra(resenjeZahteva.getSifra());

        return obradaZahteva;
    }

}
