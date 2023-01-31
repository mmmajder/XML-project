import {LiceZahtevaZig} from "./LiceZahtevaZig";
import {Zig} from "./Zig";
import {Klasa} from "./Klasa";
import {ZigDTO} from "./ZigDTO";

export class BojaDTO {
  boja: string[] = [];

  public BojaDTO(){
    this.boja = [];
  }
}

export class KlasaDTO {
  klasa: Klasa[] = [];

  public KlasaDTO(){
    this.klasa = [];
  }
}

export class NeededPrilogsDTO {
  neededPrilogs: string[] = [];

  public NeededPrilogsDTO(){
    this.neededPrilogs = [];
  }
}

export class ZahtevZaPriznanjeZigaDTO {
  podnosilacPrijave: LiceZahtevaZig = new LiceZahtevaZig();
  punomocnik: LiceZahtevaZig = new LiceZahtevaZig();
  predstavnik: LiceZahtevaZig = new LiceZahtevaZig();
  // zig: Zig = new Zig();
  // klasa: Klasa[] = [];
  zatrazenoPravoPrvenstvaIOsnov: string = "";
  // neededPrilogs: string[] = [];
  statusPrilogPunomocje: string = "";
  zigDTO: ZigDTO = new ZigDTO();
  // bojaDTO: BojaDTO = new BojaDTO;
  // klasaDTO: KlasaDTO = new KlasaDTO;
  // neededPrilogsDTO: NeededPrilogsDTO = new NeededPrilogsDTO;
  klasaConcatenated: string = "";
  neededPrilogsConcatenated: string = "";

  public ZahtevZaPriznanjeZigaDTO(){
    this.podnosilacPrijave = new LiceZahtevaZig();
    this.punomocnik = new LiceZahtevaZig();
    this.predstavnik = new LiceZahtevaZig();
    // this.zig = new Zig();
    // this.klasa = [];
    this.zatrazenoPravoPrvenstvaIOsnov = "";
    // this.neededPrilogs = [];
    this.statusPrilogPunomocje = "";
    this.zigDTO= new ZigDTO();
    // this.bojaDTO = new BojaDTO;
    // this.klasaDTO = new KlasaDTO;
    // this.neededPrilogsDTO = new NeededPrilogsDTO;
    this.klasaConcatenated = "";
    this.neededPrilogsConcatenated = "";
  }
}
