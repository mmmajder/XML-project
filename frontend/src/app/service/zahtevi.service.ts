import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import * as JsonToXML from "js2xmlparser";
import {DetaljiOZahtevu} from "../model/shared/Zahtev";

@Injectable({
  providedIn: 'root'
})
export class ZahteviService {

  private readonly autorskaPravaUrl: string;
  private readonly patentiUrl: string;
  private readonly zigoviUrl: string;

  constructor(private http: HttpClient) {
    this.patentiUrl = 'http://localhost:8000';
    this.autorskaPravaUrl = 'http://localhost:8001';
    this.zigoviUrl = 'http://localhost:8002';
  }

  public getZahtev(brojPrijave: string | null): Observable<DetaljiOZahtevu> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", brojPrijave);
    return this.http.post<DetaljiOZahtevu>(this.getUrl(brojPrijave), xmlZahtev, AuthService.getHttpOptions());
  }

  private getUrl(brojPrijave: string | null): string {
    if (brojPrijave == null) return "";
    switch (brojPrijave.at(0)) {
      case "A":
        return this.autorskaPravaUrl;
      case "P":
        return this.patentiUrl;
      default:
        return this.zigoviUrl;
    }
  }

  prihvati(brojPrijave: string | null) {
    // TODO
  }

  odbij(brojPrijave: string | null, razlogOdbijanja: string) {
    // TODO
  }
}
