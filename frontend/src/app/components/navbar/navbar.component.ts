import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {GenerateReportsDialogComponent} from "../generate-reports-dialog/generate-reports-dialog.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(private route: Router, private dialog: MatDialog) {
  }

  goToPage(page: string) {
    this.route.navigate([page]);
  }

  generateReport() {
    this.dialog.open(GenerateReportsDialogComponent, {
      width: '400px',
    });
  }
}
