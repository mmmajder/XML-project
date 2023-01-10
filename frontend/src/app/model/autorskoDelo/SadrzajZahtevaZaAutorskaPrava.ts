import {PodnosilacZahteva} from "./PodnosilacZahteva";
import {TAutor} from "./TAutor";
import {Punomocnik} from "./Punomocnik";
import {PodaciOAutorskomDelu} from "./PodaciOAutorskomDelu";

export class SadrzajZahtevaZaAutorskaPrava {
  podnosilacZahteva!: PodnosilacZahteva;
  autori: TAutor[] = [];
  podaciOPunomocniku: Punomocnik = new Punomocnik();
  autorskoDelo!: PodaciOAutorskomDelu;

  constructor(podnosioc: PodnosilacZahteva, autori: TAutor[], punomocnik: Punomocnik, delo: PodaciOAutorskomDelu) {
    this.autori = autori;
    this.autorskoDelo = delo;
    this.podnosilacZahteva = podnosioc;
    this.podaciOPunomocniku = punomocnik;
  }

  public isValid(): boolean {
    for (let a of this.autori) {
      if (!a.podaciOAutoru.autor.isValid()) {
        return false;
      }
    }
    return this.podnosilacZahteva.isValid() && this.autori.length > 0 && this.autorskoDelo.isValid();
  }
}
