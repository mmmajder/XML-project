import {Component} from '@angular/core';
import {Zahtev} from "../../model/shared/Zahtev";
import {MetadataSearchParamsDTO, TextSearchDTO} from "../../model/search/SearchParams";
import {ZahteviService} from "../../service/zahtevi.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  zigPossibleMetadata: string[] = ["Podnosilac - email", "Punomocnik - email", "Predstavnik - email", "Tip žiga", "Broj prijave", "Datum podnošenja"];
  patentPossibleMetadata: string[] = ["Broj prijave", "Podnosilac - email", "Pronalazac - email", "Punomocnik - email", "Datum podnošenja", "Vrsta prijave"];
  autorskaPravaPossibleMetadata: string[] = ["Broj prijave", "Naslov", "Email podnosioca prijave", "Vrsta autorskog dela"];
  sviMetapodaci: string[] = this.autorskaPravaPossibleMetadata;

  zigMetadataMapper = {
    "Podnosilac - email": "podnosilac_email",
    "Punomocnik - email": "punomocnik_email",
    "Predstavnik - email": "predstavnik_email",
    "Tip žiga": "tip_ziga",
    "Broj prijave": "broj_prijave",
    "Datum podnošenja": "datum_podnosenja"
  }
  autorskaPravaMetadataMapper = {
    "Broj prijave": "broj_prijave",
    "Naslov": "naslov",
    "Email podnosioca prijave": "podnosilac_email",
    "Vrsta autorskog dela": "vrsta_autorskog_dela",
  }

  patentMetadataMapper = {
    "Broj prijave": "broj_prijave",
    "Podnosilac - email": "podnosilac_email",
    "Pronalazac - email": "pronalazac_email",
    "Punomocnik - email": "punomocnik_email",
    "Datum podnošenja": "datum_podnosenja",
    "Vrsta prijave": "vrsta_prijave"
  }
  metadataMapper: any = this.autorskaPravaMetadataMapper;

  simpleSearchText: string = "";
  metapodaci: MetadataSearchParamsDTO[] = [];
  rezultatiPretrage: Zahtev[] = [];
  fifthIndexesOfResults = [0];
  searched = false;
  selected: string = "A";
  vrstaZahteva: string = "A";
  statusZahteva: string = "prihvaceni";
  matchCase: boolean = false;

  role: string = "GRADJANIN";

  constructor(private zahteviService: ZahteviService, private _snackBar: MatSnackBar, private authService: AuthService) {
    this.authService.getCurrentlyLoggedUser().subscribe((data: any) => {
      this.role = data.getElementsByTagName("role")[0].textContent;
      if (this.role === "GRADJANIN")
        this.statusZahteva = "prihvaceni";
    });
  }

  removeMeta(i: number) {
    this.metapodaci.splice(i, 1);
  }

  addMeta() {
    let metadata = new MetadataSearchParamsDTO();
    metadata.operator = "AND";
    this.metapodaci.push(new MetadataSearchParamsDTO());
    console.log(this.metapodaci.length)
  }

  searchText() {
    this.rezultatiPretrage = [];
    let textSearchParams: TextSearchDTO = new TextSearchDTO();
    textSearchParams.textSearch = this.simpleSearchText.trim();
    textSearchParams.status = this.statusZahteva;
    textSearchParams.casesensitive = this.matchCase;

    this.zahteviService.searchByText(textSearchParams, this.vrstaZahteva).subscribe(data => {
      this.rezultatiPretrage = this.parseSimpleZahtevsDoc(data);
      this.searched = true;
    });
  }

  searchMeta() {
    if (!this.validateMeta()) {
      this._snackBar.open("Metadata not valid.", '', {
        duration: 3000,
        panelClass: ['snack-bar']
      })
      return;
    }

    this.rezultatiPretrage = [];
    let metapodaciForSearch: MetadataSearchParamsDTO[] = this.mapVisibleMetadataNamesToFunctional();
    let metaParams: MetadataSearchParamsDTO = this.mapMetadataParamsToOneInstance(metapodaciForSearch);
    metaParams.status = this.statusZahteva;
    this.zahteviService.searchByMetadata(metaParams, this.vrstaZahteva).subscribe(data => {
      this.rezultatiPretrage = this.parseSimpleZahtevsDoc(data);
      this.searched = true;
    });
  }

  mapVisibleMetadataNamesToFunctional() {
    let metapodaciForSearch: MetadataSearchParamsDTO[] = JSON.parse(JSON.stringify(this.metapodaci))
    for (let meta of metapodaciForSearch) {
      meta.property = this.metadataMapper[meta.property];
    }
    return metapodaciForSearch;
  }

  mapMetadataParamsToOneInstance(metapodaciForSearch: MetadataSearchParamsDTO[]) {
    let m: MetadataSearchParamsDTO = new MetadataSearchParamsDTO();
    m.property = "";
    m.value = "";
    m.operator = "";

    for (let mp of metapodaciForSearch) {
      m.property += "|" + mp.property;
      m.value += "|" + mp.value;
      m.operator += "|" + mp.operator;
    }

    m.property = m.property.slice(1);
    m.value = m.value.slice(1);
    m.operator = m.operator.slice(1);

    return m;
  }

  setFifthIndexes(len: number) {
    this.fifthIndexesOfResults = [];
    for (let i = 0; i < len; i += 5) {
      this.fifthIndexesOfResults.push(i);
    }
  }

  validateTextSearch() {
    return !(this.simpleSearchText === null || this.simpleSearchText.trim() === "");
  }

  validateMeta() {
    for (let metaParam of this.metapodaci) {
      if (metaParam.property === null || metaParam.value === null || metaParam.operator === null
        || metaParam.property.trim() === "" || metaParam.value.trim() === "") {
        return false;
      }
    }
    return this.metapodaci.length != 0;
  }

  resetChosenParamsAndResultsAndPutPropperPossibleMetadata() {
    this.rezultatiPretrage = [];
    this.metapodaci = [];
    this.searched = false;

    if (this.vrstaZahteva === "A") {
      this.sviMetapodaci = this.autorskaPravaPossibleMetadata;
      this.metadataMapper = this.autorskaPravaMetadataMapper;
    } else if (this.vrstaZahteva === "P") {
      this.sviMetapodaci = this.patentPossibleMetadata;
      this.metadataMapper = this.patentMetadataMapper;
    } else { // Z
      this.sviMetapodaci = this.zigPossibleMetadata;
      this.metadataMapper = this.zigMetadataMapper;
    }
  }

  parseSimpleZahtevsDoc(data: any) {
    let simpleZahtevs: Zahtev[] = [];
    let simpleZahtevsDoc = data.getElementsByTagName("item"); // #document is a <List> of <item>s containing data from back [0].textContent;

    for (let simpleZahtevDoc of simpleZahtevsDoc) {
      let brojPrijaveSimpleZahtev: string = simpleZahtevDoc.getElementsByTagName("brojPrijave")[0].textContent;
      let datumPodnosenjaSimpleZahtev: string = simpleZahtevDoc.getElementsByTagName("datumPodnosenja")[0].textContent;
      let podnosiocSimpleZahtev = simpleZahtevDoc.getElementsByTagName("podnosiocEmail")[0].textContent;
      let obradjenSimpleZahtev: boolean = simpleZahtevDoc.getElementsByTagName("obradjen")[0].textContent === "true";

      let simpleZahtev = new Zahtev();
      simpleZahtev.brojPrijave = brojPrijaveSimpleZahtev;
      simpleZahtev.datumPodnosenja = datumPodnosenjaSimpleZahtev;
      simpleZahtev.podnosiocEmail = podnosiocSimpleZahtev;
      simpleZahtev.obradjen = obradjenSimpleZahtev;

      simpleZahtevs.push(simpleZahtev);
      console.log(simpleZahtev);
    }

    return simpleZahtevs;
  }
}
