import {Component, Output} from '@angular/core';
import {TAutor} from "../../model/autorskoDelo/TAutor";

@Component({
  selector: 'app-podaci-o-autoru',
  templateUrl: './podaci-o-autoru.component.html',
  styleUrls: ['./podaci-o-autoru.component.css']
})
export class PodaciOAutoruComponent {
  tipAutora: string = "ziv";

  @Output() autor: TAutor = new TAutor();
}
