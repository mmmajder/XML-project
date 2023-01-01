export class Autor {
  ime: string = "";
  prezime: string = "";
  adresa: string = "";
  pseudonim: string = "";
  drzavljanstvo: string = "";
  anoniman: boolean | undefined;
  godinaSmrti: number | undefined;

  public isValid(): boolean {
    return this.ime.length > 0 &&
      this.prezime.length > 0 &&
      this.adresa.length > 0 &&
      this.drzavljanstvo.length > 0;
  }
}
