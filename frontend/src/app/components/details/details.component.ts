import {Component, Input, OnInit} from '@angular/core';
import {DetaljiOZahtevu, ObradaZahtevaDTO} from "../../model/shared/Zahtev";
import {ZahteviService} from "../../service/zahtevi.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {bootstrapApplication} from "@angular/platform-browser";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  @Input() brojPrijave: string;
  @Input() obradjen: boolean;

  detaljiOZahtevu: DetaljiOZahtevu = new DetaljiOZahtevu();
  razlogOdbijanja: string = "";
  odbija: boolean = false;
  datumObrade = "";
  sluzbenik = "";
  sluzbenikEmail = "";
  odbijen: boolean;
  blob: Blob = new Blob();

  constructor(private servis: ZahteviService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    if (this.obradjen) {
      this.servis.getDetaljiOObradi(this.brojPrijave).subscribe({
        next: value => {
          this.datumObrade = value.getElementsByTagName("datumObrade")[0].textContent;
          this.sluzbenik = value.getElementsByTagName("name")[0].textContent;
          this.sluzbenikEmail = value.getElementsByTagName("email")[0].textContent;
          this.odbijen = value.getElementsByTagName("odbijen")[0].textContent === "true";
        },
        error: err => console.log(err)
      })
    }
  }

  public obradiZahtev(odbijen: boolean) {
    let dto = new ObradaZahtevaDTO();
    dto.brojPrijave = this.brojPrijave;
    dto.sluzbenik = {'name': 'Pera Peric', 'email': 'pera@gmail.com'}
    dto.odbijen = odbijen;
    if (odbijen)
      dto.razlogOdbijanja = this.razlogOdbijanja;
    this.servis.obradiZahtev(dto).subscribe({
      next: () => {
        this.servis.downloadResenje(dto.brojPrijave).subscribe({
          next: (data: Blob) => this.downloadFile(data, "resenje_", 'pdf', 'pdf'),
          error: () => this.snack()
        });
      },
      error: () => this.snack()
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
        next: (data) => this.downloadFile(data, 'resenje_', 'pdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadPDF() {
    this.servis.downloadPDF(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, "", 'pdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadHTML() {
    this.servis.downloadHTML(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, "", 'html', 'xhtml'),
        error: () => this.snack()
      });
  }

  downloadJSON() {
    this.servis.downloadJSON(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, "", 'json', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadRDF() {
    this.servis.downloadRDF(this.brojPrijave)
      .subscribe({
        next: (data) => this.downloadFile(data, "", 'rdf', 'pdf'),
        error: () => this.snack()
      });
  }

  downloadFile(data: Blob, prefix: string, ekstenzija: string, applicationType: string) {
    this.blob = new Blob([data], {type: 'application/' + applicationType});
    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(data);
    link.download = prefix + this.brojPrijave + "." + ekstenzija;
    link.click();
  }
}
