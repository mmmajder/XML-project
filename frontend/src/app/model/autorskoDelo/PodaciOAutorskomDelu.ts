export class PodaciOAutorskomDelu {
  naslov: string = '';
  vrsta: string = '';
  formaZapisa: string = '';
  nacinKoriscenja: string = '';
  naslovZasnivanogDela: string | undefined;
  autorZasnivanogDela: string | undefined;
  deloStvorenoURadnomOdnosu: boolean = false;

  public isValid(): boolean {
    return this.naslov.length > 0 &&
      this.vrsta.length > 0 &&
      this.formaZapisa.length > 0 &&
      this.nacinKoriscenja.length > 0;
  }
}
