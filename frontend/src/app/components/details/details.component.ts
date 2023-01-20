import {Component, Inject} from '@angular/core';
import {DetaljiOZahtevu} from "../../model/shared/Zahtev";
import {ActivatedRoute} from "@angular/router";
import {ZahteviService} from "../../service/zahtevi.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";
import {
  RazlogOdbijanjaDialogComponent
} from "../razlog-odbijanja-dialog/razlog-odbijanja-dialog.component";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
  detaljiOZahtevu: DetaljiOZahtevu = new DetaljiOZahtevu();

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
}
