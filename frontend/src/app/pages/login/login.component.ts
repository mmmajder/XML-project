import {Component} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  showForms() {
    document.getElementById("registation-form")?.classList.toggle("fade");
    document.getElementById("login-form")?.classList.toggle("fade");
  }
}
