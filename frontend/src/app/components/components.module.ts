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
import {MatSelectModule} from "@angular/material/select";
import {MatStepperModule} from "@angular/material/stepper";
import {MatRadioModule} from "@angular/material/radio";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {NavbarComponent} from "./navbar/navbar.component";
import { RequestCardComponent } from './request-card/request-card.component';
import {MatDividerModule} from "@angular/material/divider";
import { RazlogOdbijanjaDialogComponent } from './razlog-odbijanja-dialog/razlog-odbijanja-dialog.component';
import { ZahtevHtmlComponent } from './zahtev-html/zahtev-html.component';

@NgModule({
  declarations: [
    NavbarComponent,
    RequestCardComponent,
    RazlogOdbijanjaDialogComponent,
    ZahtevHtmlComponent
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
        MatRadioModule,
        MatCheckboxModule,
        MatDividerModule
    ],
    exports: [
        NavbarComponent,
        RequestCardComponent,
        ZahtevHtmlComponent
    ],
  bootstrap: []
})
export class ComponentsModule {
}
