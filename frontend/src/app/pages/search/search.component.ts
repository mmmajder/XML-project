import {Component} from '@angular/core';
import {Zahtev} from "../../model/shared/Zahtev";
import {MetadataSearchParams, MultipleMetadataSearchParams, TextSearchDTO} from "../../model/search/SearchParams";
import {ZahteviService} from "../../service/zahtevi.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  zigPossibleMetadata: string[] = ["Podnosilac - email", "Punomocnik - email", "Predstavnik - email", "Tip žiga", "Broj prijave", "Datum podnošenja"];
  patentPossibleMetadata: string[] = ["Autor", "Naslov", "Datum"];
  autorskaPravaPossibleMetadata: string[] = ["Autor", "Naslov", "Datum"];
  sviMetapodaci: string[] = this.autorskaPravaPossibleMetadata;

  zigMetadataMapper = {
    "Podnosilac - email": "podnosilac_email",
    "Punomocnik - email": "punomocnik_email",
    "Predstavnik - email": "predstavnik_email",
    "Tip žiga": "tip_ziga",
    "Broj prijave": "broj_prijave",
    "Datum podnošenja": "datum_podnosenja"
  }
  // TODO
  patentMetadataMapper = {
    "Autor": "Autor",
    "Naslov": "Naslov",
    "Datum": "Datum"
  }
  // TODO
  autorskaPravaMetadataMapper = {
    "Autor": "Autor",
    "Naslov": "Naslov",
    "Datum": "Datum"
  }
  metadataMapper:any = this.autorskaPravaMetadataMapper;

  simpleSearchText: string = "";
  metapodaci: MetadataSearchParams[] = [];
  rezultatiPretrage: Zahtev[] = [new Zahtev(), new Zahtev(), new Zahtev()];
  fifthIndexesOfResults = [0];
  searched = false;
  selected: string = "A";
  vrstaZahteva: string = "A";
  statusZahteva: string = "obradjeni";
  matchCase: boolean = false;

  constructor(private zahteviService: ZahteviService) {
  }

  removeMeta(i: number) {
    this.metapodaci.splice(i, 1);
  }

  addMeta() {
    this.metapodaci.push(new MetadataSearchParams());
    console.log(this.metapodaci.length)
  }

  searchText() {
    if (!this.validateTextSearch()){
      //TODO warning
      return;
    }

    let textSearchParams:TextSearchDTO = new TextSearchDTO();
    textSearchParams.textSearch = this.simpleSearchText.trim();
    textSearchParams.searchForNeobradjeni = this.statusZahteva === "neobradjeni";
    textSearchParams.casesensitive = this.matchCase;

    this.zahteviService.searchByText(textSearchParams, this.vrstaZahteva).subscribe(data => {
      console.log(data);
      this.simpleSearchText = "";
      this.searched = true;
    });
  }

  searchMeta() {
    if (!this.validateMeta()){
      //TODO warning
      return;
    }

    let metapodaciForSearch:MetadataSearchParams[] = this.mapVisibleMetadataNamesToFunctional();
    let metaParams:MultipleMetadataSearchParams = new MultipleMetadataSearchParams();
    metaParams.params = metapodaciForSearch;
    metaParams.searchForNeobradjeni = this.statusZahteva === "neobradjeni";

    this.zahteviService.searchByMetadata(metaParams, this.vrstaZahteva).subscribe(data => {
      console.log(data);
      this.searched = true;
    });
  }

  mapVisibleMetadataNamesToFunctional(){
    let metapodaciForSearch:MetadataSearchParams[] = JSON.parse(JSON.stringify(this.metapodaci))
    for (let meta of metapodaciForSearch){
      meta.property = this.metadataMapper[meta.property];
    }

    return metapodaciForSearch;
  }

  setFifthIndexes(len: number) {
    this.fifthIndexesOfResults = [];
    for (let i = 0; i < len; i += 5) {
      this.fifthIndexesOfResults.push(i);
    }
  }

  validateTextSearch(){
    if (this.simpleSearchText === null || this.simpleSearchText.trim() === ""){
      return false;
    }

    return true;
  }

  validateMeta(){
    for (let metaParam of this.metapodaci){
      if (metaParam.property === null || metaParam.value === null || metaParam.operator === null
        || metaParam.property.trim() === "" || metaParam.value.trim() === ""){
        return false;
      }
    }

    return true;
  }

  resetChosenParamsAndResultsAndPutPropperPossibleMetadata(){
    this.rezultatiPretrage = [];
    this.metapodaci = [];
    this.searched = false;

    if (this.vrstaZahteva === "A"){
      this.sviMetapodaci = this.autorskaPravaPossibleMetadata;
      this.metadataMapper = this.autorskaPravaMetadataMapper;
    } else if (this.vrstaZahteva === "P"){
      this.sviMetapodaci = this.patentPossibleMetadata;
      this.metadataMapper = this.patentMetadataMapper;
    } else { // Z
      this.sviMetapodaci = this.zigPossibleMetadata;
      this.metadataMapper = this.zigMetadataMapper;
    }
  }
}
