import {Component, Input, OnInit} from '@angular/core';
import {DetaljiOZahtevu, ObradaZahtevaDTO} from "../../model/shared/Zahtev";
import {ZahteviService} from "../../service/zahtevi.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthService} from "../../service/auth.service";
import {LoginResponseDto, UserTokenState} from "../../model/shared/LoginResponseDto";
import {User} from "../../model/shared/User";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit{
  @Input() brojPrijave: string;
  @Input() obradjen: boolean;

  detaljiOZahtevu: DetaljiOZahtevu = new DetaljiOZahtevu();
  razlogOdbijanja: string = "";
  odbija: boolean = false;
  blob: Blob = new Blob();
  loggedUser: any;

  ngOnInit(): void {
    if (this.loggedUser === undefined){
      this.authService.getCurrentlyLoggedUser().subscribe( (data:any) => {
        this.loggedUser = this.parseUser(data);
      });
    }
  }

  constructor(private servis: ZahteviService, private _snackBar: MatSnackBar, private authService: AuthService) {
    if (this.loggedUser === undefined){
      this.authService.getCurrentlyLoggedUser().subscribe( (data:any) => {
        this.loggedUser = this.parseUser(data);
      });
    }
    if (this.obradjen) {
      this.servis.getDetaljiOObradi(this.brojPrijave).subscribe({
        next: value => {
          console.log(value)
          this.detaljiOZahtevu = value
        },
        error: err => console.log(err)
      })
    }
  }

  public obradiZahtev(odbijen: boolean) {
    let dto = new ObradaZahtevaDTO();
    dto.brojPrijave = this.brojPrijave;
    dto.sluzbenik = {'name': this.loggedUser.name + " " + this.loggedUser.surname, 'email': this.loggedUser.email}
    dto.odbijen = odbijen;
    if (odbijen)
      dto.razlogOdbijanja = this.razlogOdbijanja;

    console.log(dto);

    this.servis.obradiZahtev(dto).subscribe(() => {
        this.servis.downloadResenje(dto.brojPrijave).subscribe({
          next: (data: Blob) => this.downloadFile(data, 'pdf', 'pdf'),
          error: () => this.snack()
        });
      })
  }

  private snack() {
    this._snackBar.open("Greška pri generisanju fajla.", '', {
      duration: 3000,
      panelClass: ['snack-bar']
    })
  }

  public odbijanjeZahteva() {
    if (this.odbija && this.razlogOdbijanja != "") {
      this.obradiZahtev(true);
    } else {
      this.odbija = true;
    }
  }

  downloadResenje() {
    this.servis.downloadResenje(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, 'pdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadPDF() {
    this.servis.downloadPDF(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, 'pdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadHTML() {
    this.servis.downloadHTML(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, 'html', 'xhtml'),
        error: () => this.snack()
      });
  }

  downloadJSON() {
    this.servis.downloadJSON(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, 'json', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadRDF() {
    this.servis.downloadRDF(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, 'rdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadFile(data: Blob, ekstenzija: string, applicationType: string) {
    this.blob = new Blob([data], {type: 'application/' + applicationType});
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(data);
    link.download = this.brojPrijave + "." + ekstenzija;
    link.click();
  }

  parseUser(data: any) {
    let user = new User();
    user.email = data.getElementsByTagName("email")[0].textContent;
    user.name = data.getElementsByTagName("name")[0].textContent;
    user.surname = data.getElementsByTagName("surname")[0].textContent;
    user.phoneNumber = data.getElementsByTagName("phoneNumber")[0].textContent;
    user.role = data.getElementsByTagName("role")[0].textContent;

    return user;
  }
}
