import {Component} from '@angular/core';
import {Zahtev} from "../../model/shared/Zahtev";

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
  simpleSearchText: string = "";
  metapodaci: Metapodatak[] = [new Metapodatak()];
  sviMetapodaci: string[] = ["Autor", "Naslov", "Datum"];
  rezultatiPretrage: Zahtev[] = [new Zahtev(), new Zahtev(), new Zahtev()];
  fifthIndexesOfResults = [0];
  searched = false;
  selected: string = "A";

  removeMeta(i: number) {
    this.metapodaci.splice(i, 1);
  }

  addMeta() {
    this.metapodaci.push(new Metapodatak());
    console.log(this.metapodaci.length)
  }

  search() {
    this.searched = true;
    // this.indexesOfResults = ...
  }

  setFifthIndexes(len: number) {
    this.fifthIndexesOfResults = [];
    for (let i = 0; i < len; i += 5) {
      this.fifthIndexesOfResults.push(i);
    }
  }
}
