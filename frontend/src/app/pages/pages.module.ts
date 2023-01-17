import {NgModule} from "@angular/core";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatTableModule} from "@angular/material/table";
import {MatCardModule} from "@angular/material/card";
import {MatSortModule} from "@angular/material/sort";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatDialogModule} from "@angular/material/dialog";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatMenuModule} from "@angular/material/menu";
import {AutorskaPravaComponent} from "./autorska-prava/autorska-prava.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {LoginFormComponent} from "./login-form/login-form.component";
import {RegistrationFormComponent} from "./registration-form/registration-form.component";
import {SearchComponent} from "./search/search.component";
import {ZigoviComponent} from "./zigovi/zigovi.component";
import {PatentiComponent} from "./patenti/patenti.component";
import {MatSelectModule} from "@angular/material/select";
import {MatStepperModule} from "@angular/material/stepper";
import {MatRadioModule} from "@angular/material/radio";
import {HOME} from "@angular/cdk/keycodes";

@NgModule({
  declarations: [
    AutorskaPravaComponent,
    HomeComponent,
    LoginComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    SearchComponent,
    ZigoviComponent,
    PatentiComponent
  ],
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    CommonModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatSelectModule,
    MatStepperModule,
    MatRadioModule
  ],
  exports: [
    HomeComponent
  ],
  bootstrap: []
})
export class PagesModule {
}
