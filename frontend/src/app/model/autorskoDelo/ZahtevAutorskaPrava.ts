import {PodnosilacZahteva} from "./PodnosilacZahteva";
import {Autor} from "./Autor";
import {Punomocnik} from "./Punomocnik";
import {PodaciOAutorskomDelu} from "./PodaciOAutorskomDelu";

export class ZahtevAutorskaPrava {
  podnosioc!: PodnosilacZahteva;
  autori: Autor[] = [];
  punomocnik: Punomocnik = new Punomocnik();
  autorskoDelo!: PodaciOAutorskomDelu;

  constructor(podnosioc: PodnosilacZahteva, autori: Autor[], punomocnik: Punomocnik, delo: PodaciOAutorskomDelu) {
    this.autori = autori;
    this.autorskoDelo = delo;
    this.podnosioc = podnosioc;
    this.punomocnik = punomocnik;
  }

  public isValid(): boolean {
    for (let a of this.autori) {
      if (!a.isValid()) {
        return false;
      }
    }
    return this.podnosioc.isValid() && this.autori.length > 0 && this.autorskoDelo.isValid();
  }
}
