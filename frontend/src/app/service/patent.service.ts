import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {mapCreatePatent} from "../utils/Mapper";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class PatentService {

  private readonly patentUrl: string;

  constructor(private http: HttpClient) {
    this.patentUrl = 'http://localhost:8000/patent';
  }

  public podnesiZahtev(zahtev: any): Observable<Object> {
    let body = mapCreatePatent(zahtev)
    console.log(body)
    return this.http.post<Object>(this.patentUrl, body, AuthService.getHttpOptions());
  }
}
