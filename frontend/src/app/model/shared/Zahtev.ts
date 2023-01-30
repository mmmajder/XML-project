import {SimpleUser} from "./User";

export class Zahtev {
  brojPrijave: string = "A-2023/27";
  datumPodnosenja: string = "20.01.2023.";
  podnosioc: SimpleUser = {
    "name": "Pera Peric",
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

export class ObradaZahtevaDTO {
  sluzbenik!: SimpleUser;
  datumObrade!: string;
  odbijen: boolean = false;
  razlogOdbijanja: string = "";
  brojPrijave!: string;
}
