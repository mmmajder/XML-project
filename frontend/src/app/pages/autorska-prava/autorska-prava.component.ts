import { Component } from '@angular/core';

@Component({
  selector: 'app-autorska-prava',
  templateUrl: './autorska-prava.component.html',
  styleUrls: ['./autorska-prava.component.css']
})
export class AutorskaPravaComponent {
  tipAutora: string = 'fizickoLice';
  ime: string = "";
  prezime: string = "";
  sediste: string = "";
  adresa: string = "";
  telefon: string = "";
  email: string = "";
  poslovnoIme: string = "";
  drzavljanstvo: string = "";

}
