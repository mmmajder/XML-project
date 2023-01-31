package com.example.patentbackend.mapper;

import com.example.patentbackend.dto.*;
import com.example.patentbackend.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mapper {
    public static Adresa mapToAdresa(AdresaDTO adresaDTO) {
        Adresa adresa = new Adresa();
        adresa.setDrzava(adresaDTO.getDrzava());
        adresa.setMesto(adresaDTO.getMesto());
        adresa.setUlica(adresaDTO.getUlica());
        adresa.setPostanskiBroj(adresaDTO.getPostanskiBroj());
        adresa.setBrojUUlici(adresaDTO.getBrojUUlici());
        return adresa;
    }

    public static Dostavljanje mapToDostavljanje(DostavljanjeDTO dostavljanjeDTO) {
        Dostavljanje dostavljanje = new Dostavljanje();
        dostavljanje.setNacinDostave(dostavljanjeDTO.getNacinDostave());
        dostavljanje.setAdresaZaDostavljanje(mapToAdresa(dostavljanjeDTO.getAdresaZaDostavljanje()));
        return dostavljanje;
    }

    public static List<NazivPronalaska> mapToListNazivPronalaska(SadrzajZahtevaZaPriznanjePatentaDTO sadrzaj) {
        List<NazivPronalaska> listNazivPronalaska = new ArrayList<>();
        NazivPronalaska nazivPronalaskaSrpski = new NazivPronalaska();
        nazivPronalaskaSrpski.setNaziv(sadrzaj.getNazivSrpski());
        nazivPronalaskaSrpski.setJezik("SRPSKI");
        listNazivPronalaska.add(nazivPronalaskaSrpski);
        NazivPronalaska nazivPronalaskaEngleski = new NazivPronalaska();
        nazivPronalaskaEngleski.setNaziv(sadrzaj.getNazivEngleski());
        nazivPronalaskaEngleski.setJezik("ENGLESKI");
        listNazivPronalaska.add(nazivPronalaskaEngleski);
        return listNazivPronalaska;
    }

    public static OsnovneInformacijeOZahtevuZaPriznanjePatenta mapToOsnovneInformacijeOZahtevuZaPriznanjePatenta(OsnovneInformacijeOZahtevuZaPriznanjePatentaDTO osnovneInformacijeOZahtevuZaPriznanjePatentaDTO) {
        OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setBrojPrijave(osnovneInformacijeOZahtevuZaPriznanjePatentaDTO.getBrojPrijave());
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(osnovneInformacijeOZahtevuZaPriznanjePatentaDTO.getDatumPrijema());
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(osnovneInformacijeOZahtevuZaPriznanjePatentaDTO.getPriznatiDatumPodnosenja());
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setStanje(osnovneInformacijeOZahtevuZaPriznanjePatentaDTO.getStanje());
        return osnovneInformacijeOZahtevuZaPriznanjePatenta;
    }

    public static PodaciOPodnosiocuPrijave mapToPodaciOPodnosiocuPrijave(PodaciOPodnosiocuPrijaveDTO podaciOPodnosiocuPrijaveDTO) {
        PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = new PodaciOPodnosiocuPrijave();
        if (podaciOPodnosiocuPrijaveDTO.getPodnosilacPrijave().getPoslovnoIme() == null) {
            podaciOPodnosiocuPrijave.setPodnosilacPrijave(mapToTFizickoLice(podaciOPodnosiocuPrijaveDTO.getPodnosilacPrijave()));
        } else {
            podaciOPodnosiocuPrijave.setPodnosilacPrijave(mapToTPravnoLice(podaciOPodnosiocuPrijaveDTO.getPodnosilacPrijave()));
        }
        podaciOPodnosiocuPrijave.setPodnosilacPrijaveJeIPronalazac(podaciOPodnosiocuPrijaveDTO.getPodnosilacPrijaveJeIPronalazac());
        return podaciOPodnosiocuPrijave;
    }

    public static PodaciOPrijavi mapToPodaciOPrijavi(PodaciOPrijaviDTO podaciOPrijaviDTO) {
        PodaciOPrijavi podaciOPrijavi = new PodaciOPrijavi();
        podaciOPrijavi.setVrstaPrijave(podaciOPrijaviDTO.getVrstaPrijave());
        podaciOPrijavi.setBrojOsnovnePrijave(podaciOPrijaviDTO.getBrojOsnovnePrijave());
        podaciOPrijavi.setDatumPodnosenjaPrijave(podaciOPrijaviDTO.getDatumPodnosenjaPrijave());
        return podaciOPrijavi;
    }

    public static PodaciOPronalazacu mapToPodaciOPronalazacu(PodaciOPronalazacuDTO podaciOPronalazacuDTO) {
        PodaciOPronalazacu podaciOPronalazacu = new PodaciOPronalazacu();
        podaciOPronalazacu.setPronalazac(mapToTFizickoLice(podaciOPronalazacuDTO.getPronalazac()));
        podaciOPronalazacu.setPronalazacNeZeliDaBudeNaveden(podaciOPronalazacuDTO.isPronalazacNeZeliDaBudeNaveden());
        return podaciOPronalazacu;
    }

    public static PodaciOPunomocniku mapToPodaciOPunomocniku(PodaciOPunomocnikuDTO podaciOPunomocnikuDTO) {
        PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
        if (podaciOPunomocnikuDTO.getPunomocnik().getPoslovnoIme() == null) {
            podaciOPunomocniku.setPunomocnik(mapToTFizickoLice(podaciOPunomocnikuDTO.getPunomocnik()));
        } else {
            podaciOPunomocniku.setPunomocnik(mapToTPravnoLice(podaciOPunomocnikuDTO.getPunomocnik()));
        }
        podaciOPunomocniku.setVrstaPunomocnika(podaciOPunomocnikuDTO.getVrstaPunomocnika());
        podaciOPunomocniku.setZajednickiPredstavnik(podaciOPunomocnikuDTO.getZajednickiPredstavnik());
        return podaciOPunomocniku;
    }

    public static RanijaPrijava mapToRanijaPrijava(RanijaPrijavaDTO ranijaPrijavaDTO) {
        RanijaPrijava ranijaPrijava = new RanijaPrijava();
        ranijaPrijava.setDatumPodnosenjaPrijave(ranijaPrijavaDTO.getDatumPodnosenjaPrijave());
        ranijaPrijava.setBrojRanijePrijave(ranijaPrijavaDTO.getBrojRanijePrijave());
        ranijaPrijava.setDvoslovnaOznaka(ranijaPrijavaDTO.getDvoslovnaOznaka());
        return ranijaPrijava;
    }

    public static List<RanijaPrijava> mapToListRanijaPrijava(List<RanijaPrijavaDTO> ranijaPrijavaDTOS) {
        List<RanijaPrijava> ranijaPrijavaList = new ArrayList<>();
        for (RanijaPrijavaDTO ranijaPrijava :
                ranijaPrijavaDTOS) {
            ranijaPrijavaList.add(mapToRanijaPrijava(ranijaPrijava));
        }
        return ranijaPrijavaList;
    }

    public static SadrzajZahtevaZaPriznanjePatenta mapToSadrzajZahtevaZaPriznanjePatenta(SadrzajZahtevaZaPriznanjePatentaDTO sadrzajZahtevaZaPriznanjePatentaDTO) {
        SadrzajZahtevaZaPriznanjePatenta sadrzaj = new SadrzajZahtevaZaPriznanjePatenta();
        sadrzaj.setDostavljanje(mapToDostavljanje(sadrzajZahtevaZaPriznanjePatentaDTO.getDostavljanje()));
        sadrzaj.setNazivPronalaska(mapToListNazivPronalaska(sadrzajZahtevaZaPriznanjePatentaDTO));
        sadrzaj.setPodaciOPodnosiocuPrijave(mapToPodaciOPodnosiocuPrijave(sadrzajZahtevaZaPriznanjePatentaDTO.getPodaciOPodnosiocuPrijave()));
        sadrzaj.setPodaciOPrijavi(mapToPodaciOPrijavi(sadrzajZahtevaZaPriznanjePatentaDTO.getPodaciOPrijavi()));
        sadrzaj.setPodaciOPronalazacu(mapToPodaciOPronalazacu(sadrzajZahtevaZaPriznanjePatentaDTO.getPodaciOPronalazacu()));
        sadrzaj.setPodaciOPunomocniku(mapToPodaciOPunomocniku(sadrzajZahtevaZaPriznanjePatentaDTO.getPodaciOPunomocniku()));
        sadrzaj.setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(mapToListRanijaPrijava(sadrzajZahtevaZaPriznanjePatentaDTO.getZahtevZaPriznanjePrvenstvaIzRanijihPrijava()));
        return sadrzaj;
    }

    public static TFizickoLice mapToTFizickoLice(TLiceDTO tFizickoLiceDTO) {
        TFizickoLice tFizickoLice = new TFizickoLice();
        tFizickoLice.setIme(tFizickoLiceDTO.getIme());
        tFizickoLice.setDrzavljanstvo(tFizickoLiceDTO.getDrzavljanstvo());
        tFizickoLice.setPrezime(tFizickoLiceDTO.getPrezime());
        tFizickoLice.setAdresaLica(mapToAdresa(tFizickoLiceDTO.getAdresaLica()));
        tFizickoLice.setBrojFaksa(tFizickoLiceDTO.getBrojFaksa());
        tFizickoLice.setBrojTelefona(tFizickoLiceDTO.getBrojTelefona());
        tFizickoLice.setEPosta(tFizickoLiceDTO.getEmail());
        return tFizickoLice;
    }

    public static TLice mapToTLice(TLiceDTO tLiceDTO) {
        TLice tLice = new TLice();
        tLice.setAdresaLica(mapToAdresa(tLiceDTO.getAdresaLica()));
        tLice.setEPosta(tLiceDTO.getEmail());
        tLice.setBrojFaksa(tLiceDTO.getBrojFaksa());
        tLice.setBrojTelefona(tLiceDTO.getBrojTelefona());
        return tLice;
    }

    public static TPravnoLice mapToTPravnoLice(TLiceDTO tPravnoLiceDTO) {
        TPravnoLice tPravnoLice = new TPravnoLice();
        tPravnoLice.setPoslovnoIme(tPravnoLiceDTO.getPoslovnoIme());
        tPravnoLice.setAdresaLica(mapToAdresa(tPravnoLiceDTO.getAdresaLica()));
        tPravnoLice.setBrojFaksa(tPravnoLiceDTO.getBrojFaksa());
        tPravnoLice.setEPosta(tPravnoLiceDTO.getEmail());
        tPravnoLice.setBrojTelefona(tPravnoLiceDTO.getBrojTelefona());
        return tPravnoLice;
    }

    public static ZahtevZaPriznanjePatenta mapToZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatentaDTO zahtevDTO) {
        ZahtevZaPriznanjePatenta zahtev = new ZahtevZaPriznanjePatenta();
        zahtev.setSadrzajZahtevaZaPriznanjePatenta(mapToSadrzajZahtevaZaPriznanjePatenta(zahtevDTO.getSadrzajZahtevaZaPriznanjePatenta()));
//        zahtev.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(mapToOsnovneInformacijeOZahtevuZaPriznanjePatenta(zahtevDTO.getOsnovneInformacijeOZahtevuZaPriznanjePatenta()));
        return zahtev;
    }


    public static List<SimpleZahtevDTO> mapToSimpleZahtevs(List<ZahtevZaPriznanjePatenta> zahtevs) {
        List<SimpleZahtevDTO> simpleZahtevDTOs = new ArrayList<>();

        for (ZahtevZaPriznanjePatenta zahtev : zahtevs) {
            SimpleZahtevDTO simpleZahtevDTO = mapToSimpleZahtev(zahtev);
            simpleZahtevDTOs.add(simpleZahtevDTO);
        }

        return simpleZahtevDTOs;
    }

    public static SimpleZahtevDTO mapToSimpleZahtev(ZahtevZaPriznanjePatenta zahtev) {
        SimpleZahtevDTO simpleZahtevDTO = new SimpleZahtevDTO();
        simpleZahtevDTO.setBrojPrijave(zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave());

        String datumPodnosenja = zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getPriznatiDatumPodnosenja().toString();
        simpleZahtevDTO.setDatumPodnosenja(datumPodnosenja);

        SimpleUserDTO podnosioc = mapToSimpleUser(zahtev.getSadrzajZahtevaZaPriznanjePatenta().getPodaciOPodnosiocuPrijave().getPodnosilacPrijave());
        simpleZahtevDTO.setPodnosioc(podnosioc);

        simpleZahtevDTO.setObradjen(!Objects.equals(zahtev.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getStanje(), "NA_CEKANJU"));

        return simpleZahtevDTO;
    }

    public static SimpleUserDTO mapToSimpleUser(TLice podnosilacZahteva) {
        SimpleUserDTO simpleUserDTO = new SimpleUserDTO();
        simpleUserDTO.setEmail(podnosilacZahteva.getEPosta());

        if (podnosilacZahteva instanceof TFizickoLice) {
            simpleUserDTO.setName(((TFizickoLice) podnosilacZahteva).getIme() +
                    " " + ((TFizickoLice) podnosilacZahteva).getPrezime());
        } else {
            simpleUserDTO.setName(((TPravnoLice) podnosilacZahteva).getPoslovnoIme());
        }

        return simpleUserDTO;
    }
}
