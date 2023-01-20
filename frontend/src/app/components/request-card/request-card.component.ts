import {Component, Input} from '@angular/core';
import {Zahtev} from "../../model/shared/Zahtev";
import {Router} from "@angular/router";

@Component({
  selector: 'app-request-card',
  templateUrl: './request-card.component.html',
  styleUrls: ['./request-card.component.css']
})
export class RequestCardComponent {
  @Input() zahtev: Zahtev = new Zahtev();

  constructor(private route: Router) {
  }

  detaljiOZahtevu() {
    this.route.navigate(["/zahtev/" + this.zahtev.brojPrijave]);
  }
}
