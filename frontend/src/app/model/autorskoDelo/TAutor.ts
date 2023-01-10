export class TAutor {
  podaciOAutoru: PodaciOAutoru = new PodaciOAutoru();
  anoniman: boolean | undefined;
}

export class PodaciOAutoru {
  pseudonim: string = "";
  autor: Autor = new Autor();
}

export class Autor {
  ime: string = "";
  prezime: string = "";
  adresa: string = "";
  drzavljanstvo: string = "";
  godinaSmrti: number | undefined;

  public isValid(): boolean {
    return this.ime.length > 0 &&
      this.prezime.length > 0 &&
      this.adresa.length > 0 &&
      this.drzavljanstvo.length > 0;
  }
}
