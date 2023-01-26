package com.example.zigbackend.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

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

    public EBoja createEBoja() {
        return EBoja.BELA;
    }

    public EOpis_izgleda_ziga createEOpis_izgleda_ziga() {
        return EOpis_izgleda_ziga.DRUGA_VRSTA_ZNAKA;
    }

    public EStatus createEStatus() {
        return EStatus.OBUSTAVLJENO;
    }

    public EStatus_priloga createEStatus_priloga() {
        return EStatus_priloga.NIJE_POTREBNO;
    }

    public EStatus_prilog_punomocje createEStatus_prilog_punomocje() {
        return EStatus_prilog_punomocje.NIJE_POTREBNO;
    }

    public ETip_priloga createETip_priloga() {
        return ETip_priloga.DOKAZ_O_PRAVU_PRVENSTVA;
    }

    public ETip_ziga createETip_ziga() {
        return ETip_ziga.INDIVIDUALNI_ZIG;
    }

    public EZatrazeno_pravo_prvenstva_i_osnov createEZatrazeno_pravo_prvenstva_i_osnov() {
        return EZatrazeno_pravo_prvenstva_i_osnov.KONVENCIJSKO;
    }
}
