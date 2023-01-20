import {Component} from '@angular/core';
import {DetaljiOZahtevu} from "../../model/shared/Zahtev";
import {ActivatedRoute} from "@angular/router";
import {ZahteviService} from "../../service/zahtevi.service";
import {MatDialog} from "@angular/material/dialog";
import {
  RazlogOdbijanjaDialogComponent
} from "../../components/razlog-odbijanja-dialog/razlog-odbijanja-dialog.component";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
  detaljiOZahtevu: DetaljiOZahtevu = new DetaljiOZahtevu();
  brojPrijave: string | null;

  constructor(private route: ActivatedRoute, private servis: ZahteviService, public dialog: MatDialog) {
    this.brojPrijave = this.route.snapshot.paramMap.get('brojPrijave');
    this.servis.getZahtev(this.brojPrijave).subscribe({
      next: value => this.detaljiOZahtevu = value,
      error: err => console.log(err)
    })
  }

  prihvatiZahtev() {
    this.servis.prihvati(this.brojPrijave);
  }

  odbijanjeZahteva() {
    this.dialog.open(RazlogOdbijanjaDialogComponent, {
      width: '400px',
      data: this.brojPrijave
    });
  }
}
