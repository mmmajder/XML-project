package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Zahtev_za_priznanje_ziga")
@XmlType(name="", propOrder={"podnosilacPrijave", "punomocnik", "predstavnik", "zig", "klasa",
        "zatrazenoPravoPrvenstvaIOsnov", "taksa", "prilog", "prilogPunomocje", "brojPrijaveZiga", "datumPodnosenja", "status"})
public class ZahtevZaPriznanjeZiga {
    @XmlElement(name="Podnosilac_prijave", required=true)
    private Lice podnosilacPrijave;
    @XmlElement(name="Punomocnik", required=true)
    private Lice punomocnik;
    @XmlElement(name="Predstavnik", required=true)
    private Lice predstavnik;
    @XmlElement(name="Zig", required=true)
    public Zig zig;
    @XmlElement(name="Klasa", required=true)
    private List<Klasa> klasa;
    @XmlElement(name="Zatrazeno_pravo_prvenstva_i_osnov", required=true)
    private EZatrazeno_pravo_prvenstva_i_osnov zatrazenoPravoPrvenstvaIOsnov;
    @XmlElement(name="Taksa", required=true)
    private Taksa taksa;
    @XmlElement(name="Prilog", required=true)
    private List<Prilog> prilog;
    @XmlElement(name="Prilog_punomocje", required=true)
    private PrilogPunomocje prilogPunomocje;
    @XmlElement(name="Broj_prijave_ziga", required=true)
    private String brojPrijaveZiga;
    @XmlElement(name="Datum_podnosenja", required=true)
    private Date datumPodnosenja;
    @XmlElement(name="Status", required=true)
    private EStatus status;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- podnosilacPrijave: ");
		buffer.append(podnosilacPrijave);
		buffer.append("\n- punomocnik: ");
		buffer.append(punomocnik);
		buffer.append("\n- predstavnik: ");
		buffer.append(predstavnik);
		buffer.append("\n- zig: ");
		buffer.append(zig);
		buffer.append("\n- klasa: ");
		buffer.append(klasa);
		buffer.append("\n- zatrazenoPravoPrvenstvaIOsnov: ");
		buffer.append(zatrazenoPravoPrvenstvaIOsnov);
		buffer.append("\n- taksa: ");
		buffer.append(taksa);
		buffer.append("\n- prilog: ");
		buffer.append(prilog);
		buffer.append("\n- prilogPunomocje: ");
		buffer.append(prilogPunomocje);
		buffer.append("\n- brojPrijaveZiga: ");
		buffer.append(brojPrijaveZiga);
		buffer.append("\n- datumPodnosenja: ");
		buffer.append(datumPodnosenja);
		return buffer.toString();
	}
}
