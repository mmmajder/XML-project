import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {mapCreatePatent} from "../utils/Mapper";
import {AuthService} from "./auth.service";
import {SadrzajZahtevaZaAutorskaPrava} from "../model/autorskoDelo/SadrzajZahtevaZaAutorskaPrava";
import * as JsonToXML from "js2xmlparser";
import {MetadataSearchParamsDTO} from "../model/search/SearchParams";
import {KlasaDTO, ZahtevZaPriznanjeZigaDTO} from "../model/zigModels/ZahtevZaPriznanjeZigaDTO";
import {LiceZahtevaZig} from "../model/zigModels/LiceZahtevaZig";
import {Adresa} from "../model/zigModels/Adresa";
import {Kontakt} from "../model/zigModels/Kontakt";
import {Zig} from "../model/zigModels/Zig";
import {Klasa} from "../model/zigModels/Klasa";
import {ZigDTO} from "../model/zigModels/ZigDTO";

@Injectable({
  providedIn: 'root'
})
export class ZigService {

  private readonly zigUrl: string;
  letters = /^[A-Za-z\s]+$/;
  phonenumber = /^[0-9\+-\\]+$/;
  email = /.+\@.+\..+/;

  constructor(private http: HttpClient) {
    this.zigUrl = 'http://localhost:8002/zig';
  }

  public postZahtev(zahtev:any): Observable<any> {
    const xmlZahtev = JsonToXML.parse("ZahtevZaPriznanjeZigaDTO", zahtev);
    console.log(xmlZahtev)
    console.log("evo saljem zahtev")
    return this.http.post<any>(this.zigUrl, xmlZahtev, this.getXmlHttpOptions());
  }

  public getZahtev(brojPrijave: string): Observable<any> {
    const xmlZahtev = JsonToXML.parse("NazivPrijaveDTO", brojPrijave);
    console.log(xmlZahtev)
    return this.http.get<any>(this.zigUrl + "/" + brojPrijave, this.getXmlHttpOptions());
  }

  public getXmlHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/xml',
      }),
      responseType: 'document' as 'json'
    };
  }

  isValidFilled(zahtev:ZahtevZaPriznanjeZigaDTO){
    let valid = true;
    valid &&= this.isValidLice(zahtev.podnosilacPrijave);
    valid &&= this.isValidLice(zahtev.punomocnik);
    valid &&= zahtev.statusPrilogPunomocje != "";
    valid &&= this.isValidLice(zahtev.predstavnik);
    valid &&= this.isValidZigDTO(zahtev.zigDTO);
    // valid &&= this.isValidKlasa();

    return valid;
  }

  isValidConsoleLog(zahtev:ZahtevZaPriznanjeZigaDTO){
    console.log(this.isValidLice(zahtev.podnosilacPrijave));
    console.log(this.isValidLice(zahtev.punomocnik));
    console.log(zahtev.statusPrilogPunomocje != "");
    console.log(this.isValidLice(zahtev.predstavnik));
    console.log(this.isValidZigDTO(zahtev.zigDTO));
    // console.log(this.isValidKlasa());
  }

  isValidLice(lice:LiceZahtevaZig){
    let valid = true;
    valid &&= this.isValidName(lice);
    valid &&= this.isValidAdresa(lice.adresa);
    valid &&= this.isValidKontakt(lice.kontakt);

    return valid;
  }

  isValidName(lice:LiceZahtevaZig) {
    if (lice.tipLica === "fizickoLice"){
      return (this.doesContainOnlyLetters(lice.ime) && this.doesContainOnlyLetters(lice.prezime));
    } else {
      return this.doesContainOnlyLetters(lice.poslovnoIme);
    }
  }

  isValidAdresa(adresa:Adresa) {
    let valid = true;
    valid &&= this.doesContainOnlyLetters(adresa.ulica);
    valid &&= adresa.postanskiBroj > 9999 && adresa.postanskiBroj < 100000;
    valid &&= this.doesContainOnlyLetters(adresa.mesto);
    valid &&= this.doesContainOnlyLetters(adresa.drzava);

    return valid;
  }

  isValidKontakt(kontakt:Kontakt) {
    let valid = true;
    valid &&= this.phonenumber.test(kontakt.telefon);
    valid &&= this.email.test(kontakt.email);
    valid &&= this.phonenumber.test(kontakt.faks);

    return valid;
  }

  isValidZigDTO(zigDTO:ZigDTO) {
    let valid = true;

    if (zigDTO.opisIzgledaZiga  === "DRUGA_VRSTA_ZNAKA") {
      valid &&= zigDTO.drugaVrstaZnakaOpis != "";
    }

    return valid;
  }

  isValidKlasa(chosenKlasas:string[]) {
    let valid = chosenKlasas.length > 0;

    return valid;
  }

  doesContainOnlyLetters(word:string): boolean{
    word = word.trim();
    return this.letters.test(word);
  }

  createTestZahtev(){
    let zahtev = new ZahtevZaPriznanjeZigaDTO();
    zahtev.podnosilacPrijave = this.createTestLice();
    zahtev.punomocnik = this.createTestLice();
    zahtev.predstavnik = this.createTestLice();
    zahtev.zigDTO = this.createTestZig();
    zahtev.statusPrilogPunomocje = "NIJE_PREDATO";
    zahtev.klasaConcatenated = "1 - Oruzje|2 - Malkarasa";
    zahtev.neededPrilogsConcatenated = "PRIMERAK_ZNAKA|SPISAK_ROBE_I_USLUGA|DOKAZ_O_UPLATI_TAKSE"
    zahtev.zatrazenoPravoPrvenstvaIOsnov = "SAJAMSKO";

    return zahtev;
  }

  createTestLice(){
    let lice  = new LiceZahtevaZig();
    lice.tipLica = "fizickoLice";
    lice.ime = "Ime";
    lice.prezime = "Prezime";
    lice.kontakt.faks = "12345";
    lice.kontakt.telefon = "12345";
    lice.kontakt.email = "12345@emal.com";
    lice.adresa.ulica = "Ulica";
    lice.adresa.broj = "23";
    lice.adresa.postanskiBroj = 11000;
    lice.adresa.mesto = "Mesto";
    lice.adresa.drzava = "Drzava";

    return lice;
  }

  createTestZig(){
    let zig = new ZigDTO();
    zig.tipZiga = "INDIVIDUALNI_ZIG";
    zig.opisZnaka = "DRUGA_VRSTA_ZNAKA";
    zig.drugaVrstaZnakaOpis = "Lep znak";
    zig.prevodZnaka = "Prevod";
    zig.bojaConcatenated = "PLAVA|ZELENA";

    return zig;
  }
}
