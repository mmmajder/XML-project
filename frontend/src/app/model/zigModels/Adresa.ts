export class Adresa {
  ulica: string = "";
  broj: string = ""; // example: 4/C , so it should be string, not number
  postanskiBroj: number = 0;
  mesto: string = "";
  drzava: string = "";

  public isValid(): boolean {
    return this.ulica.length > 0 &&
      this.broj.length > 0 &&
      this.postanskiBroj > 0 &&
      this.drzava.length > 0 &&
      this.drzava.length > 0;
  }
}
