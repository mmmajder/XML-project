import {Kontakt} from "./Kontakt";
import {Adresa} from "./Adresa";

export class LiceZahtevaZig {
  ime: string = "";
  prezime: string = "";
  poslovnoIme: string = "";
  kontakt: Kontakt = new Kontakt();
  adresa : Adresa = new Adresa();

  public isValid(): boolean {
      return this.adresa.isValid() && this.kontakt.isValid() &&
      ((this.ime.length > 0 && this.prezime.length > 0)
        || (this.poslovnoIme.length > 0));
  }
}
