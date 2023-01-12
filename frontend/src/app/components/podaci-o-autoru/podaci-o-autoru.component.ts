import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Autor} from "../../model/autorskoDelo/Autor";

@Component({
  selector: 'app-podaci-o-autoru',
  templateUrl: './podaci-o-autoru.component.html',
  styleUrls: ['./podaci-o-autoru.component.css']
})
export class PodaciOAutoruComponent {
  tipAutora: string = "ziv";

  @Input() autor = new Autor();
}
