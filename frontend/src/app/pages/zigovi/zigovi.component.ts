import {Component, OnInit} from '@angular/core';
import {ZahtevZaPriznanjeZigaDTO} from "../../model/zigModels/ZahtevZaPriznanjeZigaDTO";
import {Klasa} from "../../model/zigModels/Klasa";
import {LiceZahtevaZig} from "../../model/zigModels/LiceZahtevaZig";
import {Zig} from "../../model/zigModels/Zig";
import {Lice} from "../../model/autorskoDelo/Autor";
import {Adresa} from "../../model/zigModels/Adresa";
import {Kontakt} from "../../model/zigModels/Kontakt";
import {AutorskaPravaService} from "../../service/autorskaPrava.service";
import {ZigService} from "../../service/zig.service";
import {forkJoin, Observable} from "rxjs";


@Component({
  selector: 'app-zigovi',
  templateUrl: './zigovi.component.html',
  styleUrls: ['./zigovi.component.css']
})
export class ZigoviComponent implements OnInit  {
  zahtevZaPriznanjeZigaDTO: ZahtevZaPriznanjeZigaDTO = new ZahtevZaPriznanjeZigaDTO();
  chosenKlasas: string[] = [];
  izabraneBoje: string[] = [];
  neededPrilogs: string[] = [];

  valid: boolean = true;
  moguceBoje: string[] = ['BELA', 'CRNA', 'PLAVA', 'ZELENA', 'CRVENA', 'ZUTA', 'BRAON', 'ROZE', 'LJUBICASTA', 'SIVA', 'KREM', 'NARANDZASTA'];
  moguceKlase: string[] = ['1 - Hemijski proizvodi u industriji, nauci i poljoprivredi',
                            '2 - Boje, firnajzi, lakovi',
                            '3 - Nemedicinska kozmetika i toaletni preparati',
    '4 - Industrijska ulja i masti, vosak, maziva',
    '5 - Farmaceutski, medicinski i veterinarski proizvodi',
    '6 - Obični metali i njihove legure, rude',
    '7 - Mašine, mašinski alati, električni alati',
    '8 - Ručni alati i sprave kojima se ručno upravlja',
    '9 - Aparati',
    '10 - Hirurški, medicinski, zubarski i veterinarski instrumenti',
    '11 - Instalacije za osvetljenje, grejanje, hlađenje',
    '12 - Vozila za kretanje po zemlji, vazduhu, vodi',
    '13 - Vatreno oružje',
    '14 - Dragoceni metali i njihove rude',
    '15 - Muzički instrumenti',
    '16 - Hartija i karton',
    '17 - Guma',
    '18 - Koža i imitacija kože',
    '19 - Građevniski materijal',
    '20 - Nameštaj, ogledala, okviri za slike',
    '21 - Kućne ili kuhinjske sprave i posude',
    '22 - Konopci i uzice',
    '23 - Pređa i konac',
    '24 - Tekstil i zamena za tekstil',
    '25 - Odeća, obuća, pokrivala za glavu',
    '26 - Čipka, uzice i vez',
    '27 - Tepisi, asure, prostirke i otirači',
    '28 - Igre, igračke, predmeti za igru',
    '29 - Meso, riba, živina i divljač',
    '30 - Kafa, čaj, kakao, i zamene',
    '31 - Sirovi poljoprivredni i šumarski proizvodi',
    '32 - Pivo, bezalkoholni napici, mineralne vode, sokovi',
    '33 - Alkoholna pića izuzev piva',
    '34 - Duvan i zamene',
    '35 - Oglašavanje',
    '36 - Finansijke, monetarne i bankarske usluge',
    '37 - Građevinske usluge',
    '38 - Usluge telekomunikacija',
    '39 - Transportne usluge',
    '40 - Obrađivanje materijala',
    '41 - Obrazovne usluge' ,
    '42 - Naučne i tehnološke usluge, usluge istraživanja',
    '43 - Obezbeđivanje hrane i pića',
    '44 - Medicinske usluge',
    '45 - Pravne usluge' ]
  etipPrilogaPRIMERAK_ZNAKA = "PRIMERAK_ZNAKA";
  etipPrilogaSPISAK_ROBE_I_USLUGA = "SPISAK_ROBE_I_USLUGA";
  etipPrilogaDOKAZ_O_UPLATI_TAKSE = "DOKAZ_O_UPLATI_TAKSE";
  etipPrilogaOPSTI_AKT_O_ZIGU = "OPSTI_AKT_O_ZIGU";
  etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA = "DOKAZ_O_PRAVU_PRVENSTVA";
  prilogPRIMERAK_ZNAKA: any;
  prilogSPISAK_ROBE_I_USLUGA: any;
  prilogDOKAZ_O_UPLATI_TAKSE: any;
  prilogOPSTI_AKT_O_ZIGU: any;
  prilogDOKAZ_O_PRAVU_PRVENSTVA: any;
  prilogPUNOMOCJE: any;


  constructor(private servis: ZigService) {
  }

  ngOnInit(){
    this.resetEverything();
    // this.addAlwaysNeededPrilogTypes();
  }

  resetEverything(){
    this.zahtevZaPriznanjeZigaDTO = new ZahtevZaPriznanjeZigaDTO();
    this.chosenKlasas = [];
    this.izabraneBoje = [];
    this.neededPrilogs = [];
    this.resetUploads();
  }

  resetUploads(){
    this.prilogPRIMERAK_ZNAKA = [];
    this.prilogSPISAK_ROBE_I_USLUGA = [];
    this.prilogDOKAZ_O_UPLATI_TAKSE = [];
    this.prilogOPSTI_AKT_O_ZIGU = [];
    this.prilogDOKAZ_O_PRAVU_PRVENSTVA = [];
    this.prilogPUNOMOCJE = [];
  }

  addNeededPrilogType(prilogType:string){
    this.neededPrilogs.push(prilogType);
  }

  addAlwaysNeededPrilogTypes(){
    this.addNeededPrilogType(this.etipPrilogaPRIMERAK_ZNAKA);
    this.addNeededPrilogType( this.etipPrilogaSPISAK_ROBE_I_USLUGA);
    this.addNeededPrilogType( this.etipPrilogaDOKAZ_O_UPLATI_TAKSE);
  }

  removedNeededPrilogType(prilogType:string){
    this.neededPrilogs = this.neededPrilogs.filter(type => type !== prilogType);
  }

  onTipZigSelectionChange(type:string){
    if (type === "INDIVIDUALNI_ZIG"){
      this.removedNeededPrilogType(this.etipPrilogaOPSTI_AKT_O_ZIGU);
    } else {
      this.addNeededPrilogType( this.etipPrilogaOPSTI_AKT_O_ZIGU);
    }
  }

  onPravoPrvenstvaSelectionChange(type:string){
    if (type === ""){
      this.removedNeededPrilogType( this.etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA);
    } else {
      this.addNeededPrilogType( this.etipPrilogaDOKAZ_O_PRAVU_PRVENSTVA);
    }
  }

  concatenateKlase(){
    return this.concatenate(this.chosenKlasas);
  }

  concatenateBoje(){
    return this.concatenate(this.izabraneBoje);
  }

  concatenateNeededPrilogs(){
    return this.concatenate(this.neededPrilogs);
  }

  concatenate(elemsToConcatenate:string[]){
    let concatenated = "";

    for (let word of elemsToConcatenate){
      concatenated += "|" + word;
    }

    while (concatenated.at(0) === "|"){
      console.log(concatenated)
      concatenated = concatenated.slice(1);
    }

    return concatenated;
  }

  podnesiZahtev() {

    // let zahtev = this.servis.createTestZahtev();
    let zahtev = this.zahtevZaPriznanjeZigaDTO;
    this.addAlwaysNeededPrilogTypes();
    console.log("Valid filled: " +  this.isValid(zahtev));

    if (! this.servis.isValidFilled(zahtev)){
      return;
    }
    console.log("???")

    zahtev.klasaConcatenated = this.concatenateKlase();
    zahtev.neededPrilogsConcatenated = this.concatenateNeededPrilogs();
    zahtev.zigDTO.bojaConcatenated = this.concatenateBoje();

    console.log(zahtev.neededPrilogsConcatenated)

    this.servis.postZahtev(zahtev).subscribe(data => {
      let brojPrijaveZiga = data.getElementsByTagName("brojPrijaveZiga")[0].textContent;
      console.log(brojPrijaveZiga);
      console.log(this.prilogPUNOMOCJE);
      this.uploadPrilogsForkJoin(brojPrijaveZiga);
    });
  }

  isValid(zahtev:ZahtevZaPriznanjeZigaDTO){
    this.servis.isValidConsoleLog(zahtev);
    let valid = this.servis.isValidFilled(zahtev);
    valid &&= this.chosenKlasas.length > 0;
    valid &&= zahtev.statusPrilogPunomocje !== null && zahtev.statusPrilogPunomocje !== undefined && zahtev.statusPrilogPunomocje !== "";

    return valid;
  }

  uploadPrilogsForkJoin(brojPrijaveZiga:string){
    forkJoin(
      this.uploadPunomocje(brojPrijaveZiga),
      this.uploadPrimerZnaka(brojPrijaveZiga),
      this.uploadOpstiAkt(brojPrijaveZiga),
      this.uploadSpisakRobe(brojPrijaveZiga),
      this.uploadDokazOPrvenstvu(brojPrijaveZiga),
      this.uploadDokazTakse(brojPrijaveZiga)
    ).subscribe(data => {
      console.log("sad jos samo da se sacuva");
      this.servis.saveAfterPrilogAddition(brojPrijaveZiga).subscribe(data => {
        console.log("sacuvali smo");
      });
    });
  }

  uploadPunomocje(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.zahtevZaPriznanjeZigaDTO.statusPrilogPunomocje === "NIJE_PREDATO";
    return this.uploadPrilogNoSub("PUNOMOCJE", brojPrijaveZiga, isStatusOkay, this.prilogPUNOMOCJE);
  }

  uploadOpstiAkt(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.zahtevZaPriznanjeZigaDTO.statusPrilogPunomocje === "KOLEKTIVNI_ZIG" || this.zahtevZaPriznanjeZigaDTO.statusPrilogPunomocje === "ZIG_GARANCIJE";
    return  this.uploadPrilogNoSub("OPSTI_AKT_O_ZIGU", brojPrijaveZiga, isStatusOkay, this.prilogOPSTI_AKT_O_ZIGU);
  }

  uploadSpisakRobe(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return this.uploadPrilogNoSub("SPISAK_ROBE_I_USLUGA", brojPrijaveZiga, isStatusOkay, this.prilogSPISAK_ROBE_I_USLUGA);
  }

  uploadDokazOPrvenstvu(brojPrijaveZiga:string){
    let isStatusOkay:boolean = this.zahtevZaPriznanjeZigaDTO.zatrazenoPravoPrvenstvaIOsnov == 'KONVENCIJSKO' || this.zahtevZaPriznanjeZigaDTO.zatrazenoPravoPrvenstvaIOsnov == 'SAJAMSKO';
    return this.uploadPrilogNoSub("DOKAZ_O_PRAVU_PRVENSTVA", brojPrijaveZiga, isStatusOkay, this.prilogDOKAZ_O_PRAVU_PRVENSTVA);
  }

  uploadDokazTakse(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return  this.uploadPrilogNoSub("DOKAZ_O_UPLATI_TAKSE", brojPrijaveZiga, isStatusOkay, this.prilogDOKAZ_O_UPLATI_TAKSE);
  }

  uploadPrimerZnaka(brojPrijaveZiga:string){
    let isStatusOkay:boolean = true;
    return this.uploadPrilogNoSub("PRIMERAK_ZNAKA", brojPrijaveZiga, isStatusOkay, this.prilogPRIMERAK_ZNAKA);
  }

  uploadPrilog(prilogType:string, brojPrijaveZiga:string, isStatusOkay:boolean, prilogUploadRef:any){
    if ((isStatusOkay) && prilogUploadRef !== null){
      this.servis.postPrilog(brojPrijaveZiga, prilogType, prilogUploadRef).subscribe(data => {
        console.log(data);
        // this.resetEverything();
        prilogUploadRef = null;
      });
    }
  }

  uploadPrilogNoSub(prilogType:string, brojPrijaveZiga:string, isStatusOkay:boolean, prilogUploadRef:any){
    if ((isStatusOkay) && prilogUploadRef !== null){
      return this.servis.postPrilog(brojPrijaveZiga, prilogType, prilogUploadRef);
    } else {
      return this.servis.empty();
    }
  }

  selectPunomocjeUpload(event:any){
    this.prilogPUNOMOCJE = event.target.files[0];
  }

  selectOpstiAktUpload(event:any){
    this.prilogOPSTI_AKT_O_ZIGU = event.target.files[0];
  }

  selectSpisakRobeIUslugaUpload(event:any){
    this.prilogSPISAK_ROBE_I_USLUGA = event.target.files[0];
  }

  selectDokazOPravuPrvenstvaUpload(event:any){
    this.prilogDOKAZ_O_PRAVU_PRVENSTVA = event.target.files[0];
  }

  selectDokazOUplatiTakseUpload(event:any){
    this.prilogDOKAZ_O_UPLATI_TAKSE = event.target.files[0];
  }

  selectPrimerakZnakaUpload(event:any){
    this.prilogPRIMERAK_ZNAKA = event.target.files[0];
  }
}
