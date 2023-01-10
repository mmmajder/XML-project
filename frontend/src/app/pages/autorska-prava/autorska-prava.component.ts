import {Component} from '@angular/core';
import {TAutor} from "../../model/autorskoDelo/TAutor";
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
  autori: TAutor[] = [];
  podnosilac: PodnosilacZahteva = new PodnosilacZahteva();
  valid: boolean = true;

  constructor(private servis: AutorskaPravaService) {
  }

  dodajAutora() {
    this.autori.push(new TAutor());
  }

  ukloniAutora(index: number) {
    this.autori.splice(index, 1);
  }

  private static autorIzPodnosioca(p: PodnosilacZahteva): TAutor {
    let autor = new TAutor();
    autor.podaciOAutoru.pseudonim = p.pseudonim;
    autor.podaciOAutoru.autor.ime = p.ime;
    autor.podaciOAutoru.autor.prezime = p.prezime;
    autor.podaciOAutoru.autor.adresa = p.adresa;
    autor.podaciOAutoru.autor.drzavljanstvo = p.drzavljanstvo;
    return autor;
  }

  podnesiZahtev() {
    if (this.podnosilacJeIAutor)
      this.autori.push(AutorskaPravaComponent.autorIzPodnosioca(this.podnosilac))
    let zahtev = new SadrzajZahtevaZaAutorskaPrava(this.podnosilac, this.autori, this.punomocnik, this.autorskoDelo);
    this.valid = zahtev.isValid();
    if (this.valid) {
      this.servis.podnesiZahtev(zahtev).subscribe(data => console.log(data));
    }
  }
}
