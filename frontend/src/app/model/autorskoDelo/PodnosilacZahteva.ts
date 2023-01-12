import {Adresa} from "./Adresa";

export class PodnosilacZahteva {
  ime: string = "";
  prezime: string = "";
  adresa: Adresa = new Adresa();
  drzavljanstvo: string = "";
  poslovnoIme: string = "";
  sediste: string = "";
  telefon: string = "";
  email: string = "";
  pseudonim: string = "";

  public isValid(): boolean {
    return this.email.length > 0 &&
      this.telefon.length > 0 &&
      ((this.ime.length > 0 && this.prezime.length > 0
          && this.drzavljanstvo.length > 0 && this.adresa.isValid())
        || (this.sediste.length > 0 && this.poslovnoIme.length > 0)
      );
  }
}
