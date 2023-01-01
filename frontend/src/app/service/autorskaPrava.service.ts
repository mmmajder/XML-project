import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {ZahtevAutorskaPrava} from "../model/autorskoDelo/ZahtevAutorskaPrava";

@Injectable({
  providedIn: 'root'
})
export class AutorskaPravaService {

  private readonly autorskaPravaUrl: string;

  constructor(private http: HttpClient) {
    this.autorskaPravaUrl = 'http://localhost:8000/autorskaPrava';
  }

  public podnesiZahtev(zahtev: ZahtevAutorskaPrava): Observable<Object> {
    let body = {
      'podnosioc': zahtev.podnosioc,
      'punomocnik': zahtev.punomocnik,
      'autori': zahtev.autori,
      'autorskoDelo': zahtev.autorskoDelo
    }
    return this.http.post<Object>(this.autorskaPravaUrl + "/podnesiZahtev", body, this.getHttpOptions());
  }

  private getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type':  'application/json',
      })
    };
  }

}
