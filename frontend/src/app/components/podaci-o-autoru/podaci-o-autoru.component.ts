import {Component, Input, Output} from '@angular/core';
import {Autor} from "../../model/autorskoDelo/Autor";
import {PodnosilacZahteva} from "../../model/autorskoDelo/PodnosilacZahteva";
import {A} from "@angular/cdk/keycodes";

@Component({
  selector: 'app-podaci-o-autoru',
  templateUrl: './podaci-o-autoru.component.html',
  styleUrls: ['./podaci-o-autoru.component.css']
})
export class PodaciOAutoruComponent {
  tipAutora: string = "ziv";

  @Output() autor: Autor = new Autor();
}
