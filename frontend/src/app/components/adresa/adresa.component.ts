import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Adresa} from "../../model/autorskoDelo/Adresa";

@Component({
  selector: 'app-adresa',
  templateUrl: './adresa.component.html',
  styleUrls: ['./adresa.component.css']
})
export class AdresaComponent {
  @Input() adresa = new Adresa();
}
