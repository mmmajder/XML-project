package com.example.zigbackend.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Adresa createAdresa() {
        return new Adresa();
    }

    public FizickoLice createFizickoLice() {
        return new FizickoLice();
    }

    public Klasa createKlasa() {
        return new Klasa();
    }

    public Kontakt createKontakt() {
        return new Kontakt();
    }

    public Lice createLice() {
        return new Lice();
    }

    public PravnoLice createPravnoLice() {
        return new PravnoLice();
    }

    public Prilog createPrilog() {
        return new Prilog();
    }

    public PrilogPunomocje createPrilogPunomocje() {
        return new PrilogPunomocje();
    }

    public Taksa createTaksa() {
        return new Taksa();
    }

    public ZahtevZaPriznanjeZiga createZahtevZaPriznanjeZiga() {
        return new ZahtevZaPriznanjeZiga();
    }

    public Zig createZig() {
        return new Zig();
    }
}
