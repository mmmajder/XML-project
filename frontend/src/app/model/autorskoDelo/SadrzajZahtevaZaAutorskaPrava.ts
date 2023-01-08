import {PodnosilacZahteva} from "./PodnosilacZahteva";
import {Autor} from "./Autor";
import {Punomocnik} from "./Punomocnik";
import {PodaciOAutorskomDelu} from "./PodaciOAutorskomDelu";

export class SadrzajZahtevaZaAutorskaPrava {
  podnosilacZahteva!: PodnosilacZahteva;
  autori: Autor[] = [];
  podaciOPunomocniku: Punomocnik = new Punomocnik();
  autorskoDelo!: PodaciOAutorskomDelu;

  constructor(podnosioc: PodnosilacZahteva, autori: Autor[], punomocnik: Punomocnik, delo: PodaciOAutorskomDelu) {
    this.autori = autori;
    this.autorskoDelo = delo;
    this.podnosilacZahteva = podnosioc;
    this.podaciOPunomocniku = punomocnik;
  }

  public isValid(): boolean {
    console.log(this.podnosilacZahteva.isValid())
    console.log(this.autorskoDelo.isValid())
    console.log(this.autori)
    for (let a of this.autori) {
      if (!a.isValid()) {
        return false;
      }
    }
    console.log("VALIDAN")
    return this.podnosilacZahteva.isValid() && this.autori.length > 0 && this.autorskoDelo.isValid();
  }
}
