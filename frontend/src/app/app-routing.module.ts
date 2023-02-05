import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AutorskaPravaComponent} from "./pages/autorska-prava/autorska-prava.component";
import {PatentiComponent} from "./pages/patenti/patenti.component";
import {ZigoviComponent} from "./pages/zigovi/zigovi.component";
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {SearchComponent} from "./pages/search/search.component";

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'search', component: SearchComponent},
  {path: 'autorska-prava', component: AutorskaPravaComponent},
  {path: 'patenti', component: PatentiComponent},
  {path: 'zigovi', component: ZigoviComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
