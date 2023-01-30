import {Component} from '@angular/core';
import {Zahtev} from "../../model/shared/Zahtev";
// import {MetadataSearchParams, MultipleMetadataSearchParams, TextSearchDTO} from "../../model/search/SearchParams";
import {MetadataSearchParamsDTO, TextSearchDTO} from "../../model/search/SearchParams";
import {ZahteviService} from "../../service/zahtevi.service";
import * as JsonToXML from "js2xmlparser";
import {Meta} from "@angular/platform-browser";
import {SimpleUser} from "../../model/shared/User";

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
  metapodaci: MetadataSearchParamsDTO[] = [];
  rezultatiPretrage: Zahtev[] = [];
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
    let metadata = new MetadataSearchParamsDTO();
    metadata.operator = "AND";
    this.metapodaci.push(new MetadataSearchParamsDTO());
    console.log(this.metapodaci.length)
  }

  searchText() {
    if (!this.validateTextSearch()){
      //TODO warning
      return;
    }

    this.rezultatiPretrage = [];
    let textSearchParams:TextSearchDTO = new TextSearchDTO();
    textSearchParams.textSearch = this.simpleSearchText.trim();
    textSearchParams.searchForNeobradjeni = this.statusZahteva === "neobradjeni";
    textSearchParams.casesensitive = this.matchCase;

    this.zahteviService.searchByText(textSearchParams, this.vrstaZahteva).subscribe(data => {
      console.log(data);
      let simpleZahtevs:Zahtev[] = this.parseSimpleZahtevsDoc(data);
      this.rezultatiPretrage = simpleZahtevs;
      this.simpleSearchText = "";
      this.searched = true;
    });
  }

  searchMeta() {
    if (!this.validateMeta()){
      //TODO warning
      return;
    }

    this.rezultatiPretrage = [];
    let metapodaciForSearch:MetadataSearchParamsDTO[] = this.mapVisibleMetadataNamesToFunctional();
    let metaParams:MetadataSearchParamsDTO = this.mapMetadataParamsToOneInstance(metapodaciForSearch);
    // let metaParams:MultipleMetadataSearchParams = new MultipleMetadataSearchParams();
    // metaParams.params = metapodaciForSearch;
    metaParams.searchForNeobradjeni = this.statusZahteva === "neobradjeni";

    console.log(metaParams);
    console.log()

    // console.log(metaParams)
    // console.log()
    //
    // const xmlZahtev = JsonToXML.parse("MetadataSearchParamsDTO", metaParams);
    //
    // console.log(xmlZahtev)
    // console.log()
    //
    // let xmlZahtevPart1 = xmlZahtev.split("<params>")[0];
    // let xmlZahtevPart2And3 = xmlZahtev.split("<params>")[1];
    // let xmlZahtevPart2 = xmlZahtevPart2And3.split("</params>")[0];
    // let xmlZahtevPart3 = xmlZahtevPart2And3.split("</params>")[1];
    // console.log(xmlZahtevPart1)
    // console.log()
    // console.log(xmlZahtevPart2)
    // console.log()
    // console.log(xmlZahtevPart3)
    // console.log()
    //
    // let xmlZahtevFixed = xmlZahtevPart1 + "<params>";
    //
    // for ( let m of metaParams.params){
    //   xmlZahtevFixed += JsonToXML.parse("MetadataSearchParams", m).replaceAll("<?xml version='1.0'?>", "");
    // }
    //
    // xmlZahtevFixed += "\n</params>" + xmlZahtevPart3;
    //
    // console.log(xmlZahtevFixed)
    // console.log()

    this.zahteviService.searchByMetadata(metaParams, this.vrstaZahteva).subscribe(data => {
      console.log(data);
      let simpleZahtevs:Zahtev[] = this.parseSimpleZahtevsDoc(data);
      this.rezultatiPretrage = simpleZahtevs;
      this.searched = true;
    });
  }

  mapVisibleMetadataNamesToFunctional(){
    let metapodaciForSearch:MetadataSearchParamsDTO[] = JSON.parse(JSON.stringify(this.metapodaci))
    for (let meta of metapodaciForSearch){
      meta.property = this.metadataMapper[meta.property];
    }

    return metapodaciForSearch;
  }

  mapMetadataParamsToOneInstance(metapodaciForSearch:MetadataSearchParamsDTO[]){
    let m:MetadataSearchParamsDTO = new MetadataSearchParamsDTO();
    m.property = "";
    m.value = "";
    m.operator = "";

    for (let mp of metapodaciForSearch){
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

  parseSimpleZahtevsDoc(data:any){
    let simpleZahtevs:Zahtev[] = [];

    let simpleZahtevsDoc = data.getElementsByTagName("item"); // #document is a <List> of <item>s containing data from back [0].textContent;

    for (let simpleZahtevDoc of simpleZahtevsDoc){
      let brojPrijaveSimpleZahtev:string = simpleZahtevDoc.getElementsByTagName("brojPrijave")[0].textContent;
      let datumPodnosenjaSimpleZahtev:string = simpleZahtevDoc.getElementsByTagName("datumPodnosenja")[0].textContent;
      let podnosiocSimpleZahtev = simpleZahtevDoc.getElementsByTagName("podnosioc")[0];
      let podnosiocNameSimpleZahtev = podnosiocSimpleZahtev.getElementsByTagName("name")[0].textContent;
      let podnosiocEmailSimpleZahtev = podnosiocSimpleZahtev.getElementsByTagName("email")[0].textContent;
      let obradjenSimpleZahtev:boolean = simpleZahtevDoc.getElementsByTagName("obradjen")[0].textContent === "true";

      let simpleUser:SimpleUser = new SimpleUser();
      simpleUser.name = podnosiocNameSimpleZahtev;
      simpleUser.email = podnosiocEmailSimpleZahtev;

      let simpleZahtev = new Zahtev();
      simpleZahtev.brojPrijave = brojPrijaveSimpleZahtev;
      simpleZahtev.datumPodnosenja = datumPodnosenjaSimpleZahtev;
      simpleZahtev.podnosioc = podnosiocSimpleZahtev;
      simpleZahtev.obradjen = obradjenSimpleZahtev;

      simpleZahtevs.push(simpleZahtev);

      console.log(simpleZahtev);
    }

    return simpleZahtevs;
  }
}
