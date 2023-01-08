import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {AutorskaPravaComponent} from './pages/autorska-prava/autorska-prava.component';
import {ZigoviComponent} from './pages/zigovi/zigovi.component';
import {PatentiComponent} from './pages/patenti/patenti.component';
import {HomeComponent} from './pages/home/home.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {MatInputModule} from "@angular/material/input";
import {MatStepperModule} from "@angular/material/stepper";
import {MatRadioModule} from "@angular/material/radio";
import {FormsModule} from "@angular/forms";
import {PodaciOAutoruComponent} from './components/podaci-o-autoru/podaci-o-autoru.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDividerModule} from "@angular/material/divider";
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    AutorskaPravaComponent,
    ZigoviComponent,
    PatentiComponent,
    HomeComponent,
    PodaciOAutoruComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule,
    MatGridListModule,
    MatInputModule,
    MatStepperModule,
    MatRadioModule,
    FormsModule,
    MatCheckboxModule,
    MatDividerModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
