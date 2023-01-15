import {Component} from '@angular/core';
import {Autor} from "../../model/autorskoDelo/Autor";
import {Punomocnik} from "../../model/autorskoDelo/Punomocnik";
import {PodnosilacZahteva} from "../../model/autorskoDelo/PodnosilacZahteva";
import {AutorskaPravaService} from "../../service/autorskaPrava.service";
import {SadrzajZahtevaZaAutorskaPrava} from "../../model/autorskoDelo/SadrzajZahtevaZaAutorskaPrava";
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
  autor: Autor = new Autor();
  podnosilac: PodnosilacZahteva = new PodnosilacZahteva();
  valid: boolean = true;
  tipAutora: string = "ziv";

  constructor(private servis: AutorskaPravaService) {
  }

  private static autorIzPodnosioca(p: PodnosilacZahteva): Autor {
    let autor = new Autor();
    autor.pseudonim = p.pseudonim;
    autor.ime = p.ime;
    autor.prezime = p.prezime;
    autor.adresa = p.adresa;
    autor.drzavljanstvo = p.drzavljanstvo;
    return autor;
  }

  podnesiZahtev() {
    if (this.podnosilacJeIAutor) {
      this.autor = AutorskaPravaComponent.autorIzPodnosioca(this.podnosilac);
    }
    let zahtev = new SadrzajZahtevaZaAutorskaPrava(this.podnosilac, this.autor, this.punomocnik, this.autorskoDelo);
    this.valid = zahtev.isValid();
    if (this.valid) {
      this.servis.podnesiZahtev(zahtev).subscribe(data => console.log(data));
    }
  }
}
