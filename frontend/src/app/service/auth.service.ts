import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginResponseDto, UserTokenState} from "../model/shared/LoginResponseDto";
import {Observable} from "rxjs";
import {LoginCredentials} from "../model/shared/LoginCredentials";
import {RegisterCredentials} from "../model/shared/RegisterCredentials";
import {User} from "../model/shared/User";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly authUrl: string;

  constructor(private http: HttpClient) {
    this.authUrl = 'http://localhost:8003/auth';
  }

  public login(user: LoginCredentials): Observable<LoginResponseDto> {
    let body = {
      "email": user.email,
      "password": user.password
    }
    const xmlZahtev = JsonToXML.parse("authenticationRequest", body);
    return this.http.post<LoginResponseDto>(this.authUrl + '/login', xmlZahtev, AuthService.getHttpOptions());
  }

  public register(user: RegisterCredentials): Observable<string> {
    const xmlZahtev = JsonToXML.parse("userDTO", user);
    console.log(xmlZahtev)
    return this.http.post<string>(this.authUrl + '/register', xmlZahtev, AuthService.getHttpOptions());
  }

  public logout(token: UserTokenState | ""): Observable<Object> {
    return this.http.post(this.authUrl + '/logout', token, AuthService.getHttpOptions());
  }

  public getCurrentlyLoggedUser(): Observable<User> {
    return this.http.get<User>(this.authUrl + '/currently-logged-user', AuthService.getHttpOptions());
  }

  public static getHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
        'Content-Type': 'application/xml',
      })
    };
  }

  public static getNoContentTypeHttpOptions() {
    return {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Authorization': localStorage.getItem('token') || 'authkey',
      })
    };
  }

}
