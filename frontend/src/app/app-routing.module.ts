import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AutorskaPravaComponent} from "./pages/autorska-prava/autorska-prava.component";
import {PatentiComponent} from "./pages/patenti/patenti.component";
import {ZigoviComponent} from "./pages/zigovi/zigovi.component";
import {HomeComponent} from "./pages/home/home.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'autorska-prava', component: AutorskaPravaComponent},
  {path: 'patenti', component: PatentiComponent},
  {path: 'zigovi', component: ZigoviComponent},
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
