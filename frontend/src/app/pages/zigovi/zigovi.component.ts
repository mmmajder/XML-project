import { Component } from '@angular/core';
import {LiceZahtevaZig} from "../../model/zigModels/LiceZahtevaZig";
import {Adresa} from "../../model/zigModels/Adresa";
import {Zig} from "../../model/zigModels/Zig";
import {SadrzajZahtevaZaAutorskaPrava} from "../../model/autorskoDelo/SadrzajZahtevaZaAutorskaPrava";


@Component({
  selector: 'app-zigovi',
  templateUrl: './zigovi.component.html',
  styleUrls: ['./zigovi.component.css']
})
export class ZigoviComponent {
  tipPodnosioca: string = 'fizickoLice';
  podnosilac: LiceZahtevaZig = new LiceZahtevaZig();
  tipPunomocnika: string = 'fizickoLice';
  punomocnik: LiceZahtevaZig = new LiceZahtevaZig();
  tipPredstavnika: string = 'fizickoLice';
  predstavnik: LiceZahtevaZig = new LiceZahtevaZig();
  zig: Zig = new Zig();
  zatrazenoPravoPrvenstvaIOsnov: string = '';
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
  whatever: any = null;

  podnesiZahtev() {
  }
}
