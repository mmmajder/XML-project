import {Component} from '@angular/core';

class Metapodatak {
  operator: string = "i";
  vrsta: string = "";
  vrednost: string = "";
}

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  search: string = "";
  metapodaci: Metapodatak[] = [new Metapodatak()];
  sviMetapodaci: string[] = ["Autor", "Naslov", "Datum"];

  removeMeta(i: number) {
    this.metapodaci.splice(i, 1);
  }

  addMeta() {
    this.metapodaci.push(new Metapodatak());
    console.log(this.metapodaci.length)
  }
}
