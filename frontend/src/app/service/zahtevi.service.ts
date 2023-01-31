import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import * as JsonToXML from "js2xmlparser";
import {DetaljiOZahtevu, ObradaZahtevaDTO} from "../model/shared/Zahtev";
import {MetadataSearchParamsDTO, TextSearchDTO} from "../model/search/SearchParams";

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

  public getZahtev(brojPrijave: string | null): Observable<any> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", brojPrijave);
    return this.http.post<DetaljiOZahtevu>(this.getUrl(brojPrijave), xmlZahtev, AuthService.getHttpOptions());
  }

  private getUrl(endpointChar: string | null): string {
    if (endpointChar == null) return "";
    switch (endpointChar.at(0)) {
      case "A":
        return this.autorskaPravaUrl + "/autorskaPrava";
      case "P":
        return this.patentiUrl;
      default:
        return this.zigoviUrl + "/zig";
    }
  }

  private getObradiZahtevUrl(endpointChar: string | null): string {
    if (endpointChar == null) return "";
    switch (endpointChar.at(0)) {
      case "A":
        return "/autorskaPravaResenje/obradiZahtev";
      case "P":
        return "/obradiZahtev";
      default:
        return "/obradiZahtev";
    }
  }

  obradiZahtev(obradaZahteva: ObradaZahtevaDTO) {
    const xmlZahtev = JsonToXML.parse("obradaZahteva", obradaZahteva);
    console.log(xmlZahtev);
    return this.http.post<Blob>(this.getUrl(obradaZahteva.brojPrijave) + this.getObradiZahtevUrl(obradaZahteva.brojPrijave), xmlZahtev, AuthService.getHttpOptions());
  }

  public downloadPDF(brojPrijave: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<Blob>(this.getUrl(brojPrijave) + "/download/pdf", xmlZahtev, this.getDownloadHttpOptions());
  }

  public downloadHTML(brojPrijave: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<Blob>(this.getUrl(brojPrijave) + "/download/html", xmlZahtev, this.getDownloadHttpOptions());
  }

  public downloadRDF(brojPrijave: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<Blob>(this.getUrl(brojPrijave) + "/download/rdf", xmlZahtev, this.getDownloadHttpOptions());
  }

  public downloadJSON(brojPrijave: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<Blob>(this.getUrl(brojPrijave) + "/download/json", xmlZahtev, this.getDownloadHttpOptions());
  }

  public generisiIzvestaj(start: string, end: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("dateRange", {'start': start, 'end': end});
    return this.http.post<Blob>(this.getUrl('A') + "/download/json", xmlZahtev, this.getDownloadHttpOptions());
  }

  public getDownloadHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/xml',
      }),
      responseType: 'blob' as 'json'
    };
  }

  public searchByText(textSearchParams:TextSearchDTO, endpointChar:string): Observable<any> {
    const xmlZahtev = JsonToXML.parse("TextSearchDTO", textSearchParams);
    console.log(textSearchParams);
    console.log(xmlZahtev);
    return this.http.put<any>(this.getUrl(endpointChar) + "/text-search", xmlZahtev, this.getXmlHttpOptions());
  }

  public searchByMetadata(metaParams:MetadataSearchParamsDTO, endpointChar:string): Observable<any> {
    // const xmlZahtev = JsonToXML.parse("MetadataSearchParamsDTO", metaParams);
    const xmlZahtev = JsonToXML.parse("MetadataSearchParamsDTO", metaParams);
    console.log(xmlZahtev);
    return this.http.put<any>(this.getUrl(endpointChar) + "/metadata-search", xmlZahtev, this.getXmlHttpOptions());
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
}
