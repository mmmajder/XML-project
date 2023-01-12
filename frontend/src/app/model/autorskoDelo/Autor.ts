import {Adresa} from "./Adresa";

export class Autor {
  podaciOAutoru: PodaciOAutoru = new PodaciOAutoru();
  anoniman: boolean | undefined;
}

export class PodaciOAutoru {
  pseudonim: string = "";
  lice: Lice = new Lice();
}

export class Lice {
  ime: string = "";
  prezime: string = "";
  adresa: Adresa = new Adresa();
  drzavljanstvo: string = "";
  godinaSmrti: number | undefined;

  public isValid(): boolean {
    return this.ime.length > 0 &&
      this.prezime.length > 0 &&
      this.adresa.isValid() &&
      this.drzavljanstvo.length > 0;
  }
}
