import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";
import * as JsonToXML from "js2xmlparser";
import {DetaljiOZahtevu, ObradaZahtevaDTO, Zahtev} from "../model/shared/Zahtev";
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

  public getDetaljiOObradi(brojPrijave: string | null): Observable<any> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<any>(this.getDetaljiOZahtevuUrl(brojPrijave), xmlZahtev, this.getXmlHttpOptionsDocument());
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

  public getXmlHttpOptionsDocument() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/xml',
      }),
      responseType: 'document' as 'json'
    };
  }

  private getDetaljiOZahtevuUrl(endpointChar: string | null): string {
    if (endpointChar == null) return "";
    switch (endpointChar.at(0)) {
      case "A":
        return this.autorskaPravaUrl + "/autorskaPravaResenje/resenjeZahteva";
      case "P":
        return this.patentiUrl + "/obradiZahtev";
      default:
        return this.zigoviUrl + "/obradiZahtev";
    }
  }

  private getObradiZahtevUrl(endpointChar: string | null): string {
    if (endpointChar == null) return "";
    switch (endpointChar.at(0)) {
      case "A":
        return this.autorskaPravaUrl + "/autorskaPravaResenje/obradiZahtev";
      case "P":
        return this.patentiUrl + "/obradiZahtev";
      default:
        return this.zigoviUrl + "/obradiZahtev";
    }
  }

  obradiZahtev(obradaZahteva: ObradaZahtevaDTO) {
    const xmlZahtev = JsonToXML.parse("obradaZahteva", obradaZahteva);
    console.log(xmlZahtev);
    return this.http.post<Blob>(this.getObradiZahtevUrl(obradaZahteva.brojPrijave), xmlZahtev, AuthService.getHttpOptions());
  }

  public downloadResenje(brojPrijave: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("brojPrijave", {'broj': brojPrijave});
    return this.http.post<Blob>(this.getResenjeUrl(brojPrijave), xmlZahtev, this.getDownloadHttpOptions());
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

  public generisiIzvestaj(start: string, end: string, vrstaDokumenta: string): Observable<Blob> {
    const xmlZahtev = JsonToXML.parse("izvestajRequest", {'start': start, 'end': end});
    return this.http.post<Blob>(this.getUrl(vrstaDokumenta) + "/download/izvestaj", xmlZahtev, this.getDownloadHttpOptions());
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

  private getXMLHttpOptions(): Object {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/xml',
      }),
      responseType: 'text' as 'json'
    };
  }

  public searchByText(textSearchParams: TextSearchDTO, endpointChar: string): Observable<Zahtev> {
    const xmlZahtev = JsonToXML.parse("TextSearchDTO", textSearchParams);
    console.log(textSearchParams);
    console.log(xmlZahtev);
    return this.http.put<Zahtev>(this.getUrl(endpointChar) + "/text-search", xmlZahtev, this.getXmlHttpOptions());
  }

  public searchByMetadata(metaParams: MetadataSearchParamsDTO, endpointChar: string): Observable<Zahtev> {
    const xmlZahtev = JsonToXML.parse("MetadataSearchParamsDTO", metaParams);
    console.log(xmlZahtev);
    return this.http.put<Zahtev>(this.getUrl(endpointChar) + "/metadata-search", xmlZahtev, this.getXmlHttpOptions());
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

  private getResenjeUrl(endpointChar: string | null): string {
    if (endpointChar == null) return "";
    switch (endpointChar.at(0)) {
      case "A":
        return this.autorskaPravaUrl + "/autorskaPravaResenje/resenje";
      case "P":
        return this.patentiUrl + "/obradiZahtev";
      default:
        return this.zigoviUrl + "/obradiZahtev";
    }
  }
}
