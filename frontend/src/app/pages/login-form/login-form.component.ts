import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {LoginResponseDto, UserTokenState} from "../../model/shared/LoginResponseDto";
import {Zahtev} from "../../model/shared/Zahtev";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  @Output() switchForm = new EventEmitter();

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [Validators.required]);

  hide: boolean = true;

  email: string = "";
  password: string = "";

  constructor(private _snackBar: MatSnackBar, private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }
  switchToRegisterForm() {
    this.switchForm.emit();
  }

  login() {
    console.log(this.email)
    console.log(this.password)
    this.authService.login({
      "email": this.email,
      "password": this.password
    }).subscribe({
      next: (data:any) => {
        let loginResponseDTO:LoginResponseDto = this.parseLoginResponse(data);
        localStorage.setItem('token', "Bearer " + loginResponseDTO.token.accessToken);
        this.router.navigate(['/home']);
      }
      // error: () => this._snackBar.open("Wrong email or password.", '', {
      //   duration: 3000,
      //   panelClass: ['snack-bar']
      // })
    });
  }

  parseLoginResponse(data: any) {
    let loginResponseDTO:LoginResponseDto = new LoginResponseDto();
    loginResponseDTO.token = new UserTokenState();
    loginResponseDTO.token.accessToken = data.getElementsByTagName("accessToken");
    loginResponseDTO.token.expiresIn = data.getElementsByTagName("expiresIn");
    loginResponseDTO.userRole = data.getElementsByTagName("userRole");

    return loginResponseDTO;
  }
}
