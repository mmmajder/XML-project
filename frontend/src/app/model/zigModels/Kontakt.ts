export class Kontakt {
  telefon: string  = "";
  email: string  = "";
  faks: string  = "";

  public isValid(): boolean {
    return this.telefon.length > 0 &&
      this.email.length > 0 &&
      this.faks.length > 0;
  }

  public Kontakt(){
    this.telefon = "";
    this.email = "";
    this.faks = "";
  }
}
