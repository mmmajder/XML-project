import {Component, Inject} from '@angular/core';
import {DetaljiOZahtevu} from "../../model/shared/Zahtev";
import {ZahteviService} from "../../service/zahtevi.service";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
  detaljiOZahtevu: DetaljiOZahtevu = new DetaljiOZahtevu();
  blob: Blob = new Blob();

  constructor(@Inject(MAT_DIALOG_DATA) public brojPrijave: string, private servis: ZahteviService) {
    // this.servis.getZahtev(this.brojPrijave).subscribe({
    //   next: value => this.detaljiOZahtevu = value,
    //   error: err => console.log(err)
    // })
  }

  prihvatiZahtev() {
    this.servis.prihvati(this.brojPrijave);
  }

  odbijanjeZahteva() {
    // this.dialog.open(RazlogOdbijanjaDialogComponent, {
    //   width: '400px',
    //   data: this.brojPrijave
    // });
  }

  downloadPDF() {
    this.servis.downloadPDF(this.detaljiOZahtevu.zahtev.brojPrijave)
      .subscribe({
        next: (data) => {
          this.blob = new Blob([data], {type: 'application/pdf'});
          var downloadURL = window.URL.createObjectURL(data);
          var link = document.createElement('a');
          link.href = downloadURL;
          link.download = this.brojPrijave + ".pdf";
          link.click();
        }
      });
  }

  downloadHTML() {
    this.servis.downloadHTML(this.detaljiOZahtevu.zahtev.brojPrijave)
      .subscribe({
        next: (data) => {
          this.blob = new Blob([data], {type: 'application/xhtml'});
          var downloadURL = window.URL.createObjectURL(data);
          var link = document.createElement('a');
          link.href = downloadURL;
          link.download = this.brojPrijave + ".html";
          link.click();
        }
      });
  }

  downloadJSON() {
    // this.servis.downloadHTML(this.detaljiOZahtevu.zahtev.brojPrijave)
    //   .subscribe({
  }

  downloadRDF() {
    // this.servis.downloadHTML(this.detaljiOZahtevu.zahtev.brojPrijave)
    //   .subscribe({
  }

}
