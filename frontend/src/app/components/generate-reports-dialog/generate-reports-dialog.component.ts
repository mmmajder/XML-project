import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ZahteviService} from "../../service/zahtevi.service";

@Component({
  selector: 'app-generate-reports-dialog',
  templateUrl: './generate-reports-dialog.component.html',
  styleUrls: ['./generate-reports-dialog.component.css']
})
export class GenerateReportsDialogComponent {
  pocetak: Date = new Date();
  kraj: Date = new Date();
  dateRange = new FormGroup({
    start: new FormControl(new Date()),
    end: new FormControl(new Date()),
  });
  vrstaZahteva: string = "A";

  constructor(private servis: ZahteviService) {
  }

  generisi() {
    let start = this.pocetak.toISOString().split('T')[0];
    let end = this.kraj.toISOString().split('T')[0];
    this.servis.generisiIzvestaj(start, end, this.vrstaZahteva).subscribe({
      next: value => console.log("TO DO")
    });
  }

}
