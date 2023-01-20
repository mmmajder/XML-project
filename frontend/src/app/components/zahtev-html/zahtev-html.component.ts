import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-zahtev-html',
  templateUrl: './zahtev-html.component.html',
  styleUrls: ['./zahtev-html.component.css']
})
export class ZahtevHtmlComponent {
  @Input() brojPrijave: string | null;

  constructor() {

  }

}
