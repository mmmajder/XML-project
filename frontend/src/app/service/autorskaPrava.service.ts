import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
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
    const xmlZahtev = JsonToXML.parse("zahtevZaAutorskaPravaDTO", zahtev);
    console.log(xmlZahtev)
    return this.http.post<Object>(this.autorskaPravaUrl, xmlZahtev, AuthService.getHttpOptions());
  }

  public dobaviZahtev(brojPrijave: string): Observable<Object> {
    return this.http.get<Object>(this.autorskaPravaUrl + "/" + brojPrijave, AuthService.getHttpOptions());
  }

  public postPrilog(brojPrijaveZiga: string, tipPrilog: string, file: any) {
    let formData = new FormData();
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");
    formData.append("file", file);

    return this.http.post<Object>(this.autorskaPravaUrl + "/file-upload/" + brojPrijaveZigaParts[0] + "-" + brojPrijaveZigaParts[1] + "-" + tipPrilog, formData, this.getNoContentTypeHttpOptions());
  }

  public saveAfterPrilogAddition(brojPrijaveZiga: string) {
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");
    return this.http.get<Object>(this.autorskaPravaUrl + "/save/" + brojPrijaveZigaParts[0] + "-" + brojPrijaveZigaParts[1], AuthService.getHttpOptions());
  }

  public getNoContentTypeHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
      })
    };
  }

}
