import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {SadrzajZahtevaZaAutorskaPrava} from "../model/autorskoDelo/SadrzajZahtevaZaAutorskaPrava";
import {AuthService} from "./auth.service";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class AutorskaPravaService {

  private readonly autorskaPravaUrl: string;

  constructor(private http: HttpClient) {
    this.autorskaPravaUrl = 'http://localhost:8001/autorskaPrava';
  }

  public podnesiZahtev(zahtev: SadrzajZahtevaZaAutorskaPrava): Observable<Object> {
    console.log("PODNOSENJE ZAHTEVA", zahtev)
    // const json = JSON.stringify(zahtev);
    //
    // const xml = json2xml(json, {compact: true, spaces: 4});
    // let xml = this.OBJtoXML(zahtev);
    const xmlZahtev = JsonToXML.parse("zahtevZaAutorskaPravaDTO", zahtev);
    console.log(xmlZahtev)
    return this.http.post<Object>(this.autorskaPravaUrl, xmlZahtev, AuthService.getHttpOptions());
  }

  public dobaviZahtev(brojPrijave: string): Observable<Object> {
    return this.http.get<Object>(this.autorskaPravaUrl + "/" + brojPrijave, AuthService.getHttpOptions());
  }

}
