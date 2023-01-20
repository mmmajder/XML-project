import {SimpleUser} from "./User";

export class Zahtev {
  brojPrijave: string = "A-200/2019";
  datumPodnosenja: string = "20.01.2023.";
  podnosioc: SimpleUser = {
    "name": "Pera",
    "surname": "Peric",
    "email": "pera@gmail.com"
  };
  obradjen: boolean = false;
}

export class DetaljiOZahtevu {
  zahtev: Zahtev = new Zahtev();
  obrada: ObradaZahteva | undefined;
}

export class ObradaZahteva {
  sluzbenik!: SimpleUser;
  datumObrade!: string;
  odbijen: boolean = false;
  razlogOdbijanja: string = "";
}
