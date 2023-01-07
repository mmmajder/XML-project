import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {mapCreatePatent} from "../utils/Mapper";

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
    return this.http.post<Object>(this.patentUrl, body, this.getHttpOptions());
  }

  private getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type':  'application/xml',
      })
    };
  }

}
