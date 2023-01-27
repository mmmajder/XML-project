package com.example.zigbackend.dto;

import com.example.zigbackend.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class ZahtevZaPriznanjeZigaDTO {
    private LiceDTO podnosilacPrijave;
    private LiceDTO punomocnik;
    private LiceDTO predstavnik;
//    public Zig zig;
    public ZigDTO zigDTO;
//    @XmlElementWrapper(name = "klasaDTO")
//    @XmlElement(name = "klasa")
//    private List<Klasa> klasa;
    private String klasaConcatenated;
    private EZatrazeno_pravo_prvenstva_i_osnov zatrazenoPravoPrvenstvaIOsnov;
//    @XmlElementWrapper(name = "neededPrilogsDTO")
//    @XmlElement(name = "neededPrilogs")
//    private List<String> neededPrilogs;
    private String neededPrilogsConcatenated;
    private String statusPrilogPunomocje;
//    private NeededPrilogsDTO neededPrilogsDTO;

    // does not have Taksa, Broj_prijave_ziga, Datum_podnosenja and Status
}
