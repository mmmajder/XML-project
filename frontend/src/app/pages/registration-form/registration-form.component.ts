import {Component, EventEmitter, Output} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormControl, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent {
  @Output() switchForm = new EventEmitter();

  email: string = "";
  phoneNumber: string = "";
  password: string = "";
  password2: string = "";
  name: string = "";
  lastName: string = "";
  userRole: string = "gradjanin";

  hide: boolean = true;
  hide2: boolean = true;

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  phoneFormControl = new FormControl('', [Validators.required]);
  nameFormControl = new FormControl('', [Validators.required]);
  lastNameFormControl = new FormControl('', [Validators.required]);
  passwordFormControl = new FormControl('', [Validators.required, Validators.minLength(6)]);
  password2FormControl = new FormControl('', [Validators.required]);

  constructor(private _snackBar: MatSnackBar, private authService: AuthService) {
  }

  registerNewUser() {
    this.authService.register({
      "email": this.email,
      "password": this.password,
      "name": this.name,
      "lastName": this.lastName,
      "phoneNumber": this.phoneNumber,
      "userRole": this.userRole
    }).subscribe({
      next: () => {
        this._snackBar.open("We sent you registration link", '', {
          duration: 3000,
          panelClass: ['snack-bar']
        })
      },
      error: () => this._snackBar.open("Wrong email or password.", '', {
        duration: 3000,
        panelClass: ['snack-bar']
      })
    });
  }

  switchToLoginForm() {
    this.switchForm.emit();
  }
}
