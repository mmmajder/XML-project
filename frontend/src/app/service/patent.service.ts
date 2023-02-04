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

  public postPrilog(brojPrijave: string, tipPrilog: string, file: any) {
    let formData = new FormData();
    console.log(brojPrijave);
    let brojPrijaveParts: string[] = brojPrijave.split("/");
    formData.append("file", file);
    return this.http.post<Object>(this.patentUrl + "/file-upload/" +
      brojPrijaveParts[0] + "/" + brojPrijaveParts[1] + "/" + tipPrilog, formData, this.getNoContentTypeHttpOptions());
  }

  public saveAfterPrilogAddition(brojPrijaveZiga: string) {
    let brojPrijaveZigaParts: string[] = brojPrijaveZiga.split("/");
    return this.http.get<Object>(this.patentUrl + "/save/" + brojPrijaveZigaParts[0] + "-" + brojPrijaveZigaParts[1], AuthService.getHttpOptions());
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
