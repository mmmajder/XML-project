import {LiceZahtevaZig} from "./LiceZahtevaZig";
import {Zig} from "./Zig";
import {Klasa} from "./Klasa";
import {Taksa} from "./Taksa";
import {Prilog} from "./Prilog";
import {PrilogPunomocje} from "./PrilogPunomocje";

export class ZahtevZaPriznanjeZiga {
  podnosilacPrijave: LiceZahtevaZig;
  punomocnik: LiceZahtevaZig;
  predstavnik: LiceZahtevaZig;
  zig: Zig;
  klasa: Klasa[];
  zatrazenoPravoPrvenstvaIOsnov: string;
  taksa: Taksa;
  prilog: Prilog[];
  prilogPunomocje: PrilogPunomocje;
  brojPrijaveZiga: string;
  datumPodnosenja: any;
  status: string;
}
