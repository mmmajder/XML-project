import {Component, Inject} from '@angular/core';
import {ZahteviService} from "../../service/zahtevi.service";
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-razlog-odbijanja-dialog',
  templateUrl: './razlog-odbijanja-dialog.component.html',
  styleUrls: ['./razlog-odbijanja-dialog.component.css']
})
export class RazlogOdbijanjaDialogComponent {
  razlog: string = "";

  constructor(@Inject(MAT_DIALOG_DATA) public brojPrijave: string | null, private servis: ZahteviService) {
  }

  odbijZahtev() {
    this.servis.odbij(this.brojPrijave, this.razlog);
  }
}
