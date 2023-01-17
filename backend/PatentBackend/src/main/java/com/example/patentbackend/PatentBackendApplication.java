package com.example.patentbackend;

import com.example.patentbackend.model.*;
import com.example.patentbackend.transformer.PatentTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatentBackendApplication {

    public static void main(String[] args) {

//        SpringApplication.run(PatentBackendApplication.class, args);
        ZahtevZaPriznanjePatenta p = kreirajZahtevZaPriznanjePatenta();
//        System.out.println(p);
        PatentTransformer.generateHTMLPatent(p);
        System.out.println("Izgenerisan HTML");
        PatentTransformer.generatePDFPatent(p);
        System.out.println("Izgenerisan PDF");
    }

    private static ZahtevZaPriznanjePatenta kreirajZahtevZaPriznanjePatenta() {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = new ZahtevZaPriznanjePatenta();
        zahtevZaPriznanjePatenta.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(kreirajOsnovneInformacije());
        zahtevZaPriznanjePatenta.setSadrzajZahtevaZaPriznanjePatenta(kreirajSadrzajZahtevaZaPrizanjePatenta());
        return zahtevZaPriznanjePatenta;
    }

    private  static SadrzajZahtevaZaPriznanjePatenta kreirajSadrzajZahtevaZaPrizanjePatenta() {
        SadrzajZahtevaZaPriznanjePatenta sadrzajZahtevaZaPriznanjePatenta = new SadrzajZahtevaZaPriznanjePatenta();
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPodnosiocuPrijave(kreirajPodatkeOPodnosiocuPrijave());
        sadrzajZahtevaZaPriznanjePatenta.setDostavljanje(kreirajDostavljanje());
        sadrzajZahtevaZaPriznanjePatenta.setNazivPronalaska(kreirajNazivPronalaska());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPrijavi(kreirajPodatkeOPrijavi());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPronalazacu(kreirajPotatkeOPronalazacu());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPunomocniku(kreirajPodatkeOPunomocniku());
        sadrzajZahtevaZaPriznanjePatenta.setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(kreirajZahtevZaPriznanjePrvenstva());
        return sadrzajZahtevaZaPriznanjePatenta;
    }

    private  static List<RanijaPrijava> kreirajZahtevZaPriznanjePrvenstva() {
        RanijaPrijava ranijaPrijava1 = new RanijaPrijava();
        ranijaPrijava1.setBrojRanijePrijave("P-1231/2019");
        ranijaPrijava1.setDatumPodnosenjaPrijave(new Date(2000,2,2));
        ranijaPrijava1.setDvoslovnaOznaka("NS");

        RanijaPrijava ranijaPrijava2 = new RanijaPrijava();
        ranijaPrijava2.setBrojRanijePrijave("P-32312/2019");
        ranijaPrijava2.setDatumPodnosenjaPrijave(new Date(2000,5,2));
        ranijaPrijava2.setDvoslovnaOznaka("NS");

        ArrayList<RanijaPrijava> listaRanijihPrijava = new ArrayList<>();
        listaRanijihPrijava.add(ranijaPrijava1);
        listaRanijihPrijava.add(ranijaPrijava2);
        return listaRanijihPrijava;
    }

    private  static PodaciOPunomocniku kreirajPodatkeOPunomocniku() {
        TPravnoLice punomocnik = new TPravnoLice();
        Adresa adresa = new Adresa();
        adresa.setBrojUUlici(3);
        adresa.setDrzava("Srbija");
        adresa.setMesto("Novi Sad");
        adresa.setPostanskiBroj(21000);
        adresa.setUlica("Bulevar Evrope");
        punomocnik.setAdresaLica(adresa);
        punomocnik.setBrojFaksa("321321");
        punomocnik.setBrojTelefona("+381 12315466");
        punomocnik.setEPosta("Mejlpunomocnika@gmial.com");
        punomocnik.setPoslovnoIme("Sverc komerc");
        PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
        podaciOPunomocniku.setPunomocnik(punomocnik);
        podaciOPunomocniku.setVrstaPunomocnika("PUNOMOCNIK_ZA_PRIJEM_PISMENA");
        podaciOPunomocniku.setZajednickiPredstavnik(false);
        return podaciOPunomocniku;
    }

    private  static PodaciOPronalazacu kreirajPotatkeOPronalazacu() {
        PodaciOPronalazacu podaciOPronalazacu = new PodaciOPronalazacu();
        podaciOPronalazacu.setPronalazacNeZeliDaBudeNaveden(false);
        TFizickoLice pronalazac = new TFizickoLice();
        pronalazac.setDrzavljanstvo("Srpsko");
        pronalazac.setIme("Pera");
        pronalazac.setPrezime("Peric");
        Adresa adresaLica= new Adresa();
        adresaLica.setBrojUUlici(2);
        adresaLica.setDrzava("Crna Gora");
        adresaLica.setMesto("Niksic");
        adresaLica.setPostanskiBroj(432423);
        adresaLica.setUlica("Bla bla");
        pronalazac.setAdresaLica(adresaLica);
        pronalazac.setBrojFaksa("4234234");
        pronalazac.setBrojTelefona("+381 1651326");
        pronalazac.setEPosta("mejlpronalazaca@gmail.com");
        podaciOPronalazacu.setPronalazac(pronalazac);
        return podaciOPronalazacu;
    }

    private static  PodaciOPrijavi kreirajPodatkeOPrijavi() {
        PodaciOPrijavi podaciOPrijavi = new PodaciOPrijavi();
        podaciOPrijavi.setBrojOsnovnePrijave("P-432/2022");
        podaciOPrijavi.setDatumPodnosenjaPrijave(new Date(2000,3,4));
        podaciOPrijavi.setVrstaPrijave("IZDVOJENA");
        return podaciOPrijavi;
    }

    private  static List<NazivPronalaska> kreirajNazivPronalaska() {
        ArrayList<NazivPronalaska> nazivi = new ArrayList<>();
        NazivPronalaska nazivPronalaska = new NazivPronalaska();
        nazivPronalaska.setJezik("SRPSKI");
        nazivPronalaska.setNaziv("Novi izum");
        NazivPronalaska nazivPronalaska2 = new NazivPronalaska();
        nazivPronalaska2.setJezik("ENGLESKI");
        nazivPronalaska2.setNaziv("New invention");
        nazivi.add(nazivPronalaska);
        nazivi.add(nazivPronalaska2);
        return nazivi;
    }

    private static  Dostavljanje kreirajDostavljanje() {
        Dostavljanje dostavljanje = new Dostavljanje();
        dostavljanje.setNacinDostave("PAPIRNA_FORM");
        return dostavljanje;
    }

    private  static PodaciOPodnosiocuPrijave kreirajPodatkeOPodnosiocuPrijave() {
        TFizickoLice podnosilacPrijave = new TFizickoLice();
        podnosilacPrijave.setDrzavljanstvo("Srpsko");
        podnosilacPrijave.setIme("Zika");
        podnosilacPrijave.setPrezime("Zikic");
        Adresa adresa = new Adresa();
        adresa.setBrojUUlici(3);
        adresa.setDrzava("Srbija");
        adresa.setMesto("Novi Sad");
        adresa.setPostanskiBroj(21000);
        adresa.setUlica("Bulevar Oslobodjenja");
        podnosilacPrijave.setAdresaLica(adresa);
        podnosilacPrijave.setBrojFaksa("33232121321");
        podnosilacPrijave.setBrojTelefona("+381 1231434");
        podnosilacPrijave.setEPosta("Mejlmejl@gmial.com");
        PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = new PodaciOPodnosiocuPrijave();
        podaciOPodnosiocuPrijave.setPodnosilacPrijave(podnosilacPrijave);
        podaciOPodnosiocuPrijave.setPodnosilacPrijaveJeIPronalazac(false);
        return podaciOPodnosiocuPrijave;
    }

    private static  OsnovneInformacijeOZahtevuZaPriznanjePatenta kreirajOsnovneInformacije() {
        OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setBrojPrijave("P-1314/2019");
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(new Date(2000, 1, 1));
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(new Date(2000, 4, 5));
        return osnovneInformacijeOZahtevuZaPriznanjePatenta;
    }

}
