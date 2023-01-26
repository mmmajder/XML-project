package com.example.zigbackend.dto;

import com.example.zigbackend.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ZahtevZaPriznanjeZigaDTO {
    private Lice podnosilacPrijave;
    private Lice punomocnik;
    private Lice predstavnik;
    public Zig zig;
    private List<Klasa> klasa;
    private EZatrazeno_pravo_prvenstva_i_osnov zatrazenoPravoPrvenstvaIOsnov;
    private List<Prilog> prilog;
    private PrilogPunomocje prilogPunomocje;
    // does not have Broj_prijave_ziga, Datum_podnosenja and Status
}
