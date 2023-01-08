import { Component } from '@angular/core';
import {Autor} from "../../model/autorskoDelo/Autor";
import {Punomocnik} from "../../model/autorskoDelo/Punomocnik";
import {PodnosilacZahteva} from "../../model/autorskoDelo/PodnosilacZahteva";
import {AutorskaPravaService} from "../../service/autorskaPrava.service";
import {ZahtevAutorskaPrava} from "../../model/autorskoDelo/ZahtevAutorskaPrava";
import {PodaciOAutorskomDelu} from "../../model/autorskoDelo/PodaciOAutorskomDelu";

@Component({
  selector: 'app-autorska-prava',
  templateUrl: './autorska-prava.component.html',
  styleUrls: ['./autorska-prava.component.css']
})
export class AutorskaPravaComponent {
  tipPodnosiocaZahteva: string = 'fizickoLice';
  punomocnik: Punomocnik = new Punomocnik();
  podnosilacJeIAutor: boolean = false;
  pseudonim: string = "";
  autorskoDelo: PodaciOAutorskomDelu = new PodaciOAutorskomDelu();
  autori: Autor[] = [];
  podnosilac: PodnosilacZahteva = new PodnosilacZahteva();
  valid: boolean = true;

  constructor(private servis: AutorskaPravaService) {
  }

  dodajAutora() {
    this.autori.push(new Autor());
  }

  ukloniAutora(index: number) {
    this.autori.splice(index, 1);
  }

  podnesiZahtev() {
    let zahtev = new ZahtevAutorskaPrava(this.podnosilac, this.autori, this.punomocnik, this.autorskoDelo);
    this.valid = zahtev.isValid();
    if (this.valid) {
      this.servis.podnesiZahtev(zahtev);
    }
  }
}
