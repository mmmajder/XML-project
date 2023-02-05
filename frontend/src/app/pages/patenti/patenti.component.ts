import {Component, OnInit} from '@angular/core';
import {PatentService} from "../../service/patent.service";
import {MatSnackBar} from "@angular/material/snack-bar";

declare const Xonomy: any;

@Component({
  selector: 'app-patenti',
  templateUrl: './patenti.component.html',
  styleUrls: ['./patenti.component.css']
})
export class PatentiComponent implements OnInit {

  prilogPodnosioc: any
  prilogPravoPriajve: any

  constructor(private patentService: PatentService, private _snackBar: MatSnackBar) {
  }

  save(): void {
    let xml = Xonomy.harvest();
    console.log(xml)
    this.patentService.podnesiZahtev(xml, this.prilogPodnosioc, this.prilogPravoPriajve).subscribe({
      next: data => {
        console.log(data)
        let brojPrijavePatenta = data.getElementsByTagName("brojPrijave")[0].textContent;
        this.uploadPrilogsForkJoin(brojPrijavePatenta);
      },
      error: () => {
      }
    })

  }

  ngOnInit(): void {
    const xml = "<Zahtev_za_priznanje_patenta>" +
      "<Sadrzaj_zahteva_za_priznanje_patenta>" +
      "<Naziv_pronalaska Jezik=\"SRPSKI\"></Naziv_pronalaska>" +
      "<Naziv_pronalaska Jezik=\"ENGLESKI\"></Naziv_pronalaska>" +
      "<Podaci_o_podnosiocu_prijave>" +
      "<Podnosilac_prijave_je_i_pronalazac></Podnosilac_prijave_je_i_pronalazac>" +
      "<Podnosilac_prijave>" +
      // "<Ime_ili_poslovno_ime></Ime_ili_poslovno_ime>" +
      "<Adresa_lica>" +
      "<Ulica></Ulica>" +
      "<Broj_u_ulici></Broj_u_ulici>" +
      "<Postanski_broj></Postanski_broj>" +
      "<Mesto></Mesto>" +
      "<Drzava></Drzava>" +
      "</Adresa_lica>" +
      "<Broj_telefona></Broj_telefona>" +
      "<Broj_faksa></Broj_faksa>" +
      "<E_posta></E_posta>" +
      // Ime, prezime, poslovno ime
      "<Drzavljanstvo></Drzavljanstvo>" +
      "</Podnosilac_prijave>" +
      "</Podaci_o_podnosiocu_prijave>" +

      "<Podaci_o_pronalazacu>" +
      "<Pronalazac_ne_zeli_da_bude_naveden></Pronalazac_ne_zeli_da_bude_naveden>" +
      "<Pronalazac>" +
      "<Adresa_lica>" +
      "<Ulica></Ulica>" +
      "<Broj_u_ulici></Broj_u_ulici>" +
      "<Postanski_broj></Postanski_broj>" +
      "<Mesto></Mesto>" +
      "<Drzava></Drzava>" +
      "</Adresa_lica>" +
      "<Broj_telefona></Broj_telefona>" +
      "<Broj_faksa></Broj_faksa>" +
      "<E_posta></E_posta>" +
      "<Ime></Ime>" +
      "<Prezime></Prezime>" +
      "<Drzavljanstvo></Drzavljanstvo>" +
      "</Pronalazac>" +
      "</Podaci_o_pronalazacu>" +

      "<Podaci_o_punomocniku>" +
      "<Vrsta_punomocnika></Vrsta_punomocnika>" +
      "<Zajednicki_predstavnik></Zajednicki_predstavnik>" +
      "<Punomocnik>" +
      // "<Ime_ili_poslovno_ime></Ime_ili_poslovno_ime>" +
      "<Adresa_lica>" +
      "<Ulica></Ulica>" +
      "<Broj_u_ulici></Broj_u_ulici>" +
      "<Postanski_broj></Postanski_broj>" +
      "<Mesto></Mesto>" +
      "<Drzava></Drzava>" +
      "</Adresa_lica>" +
      "<Broj_telefona></Broj_telefona>" +
      "<Broj_faksa></Broj_faksa>" +
      "<E_posta></E_posta>" +
      // Ime, prezime, poslovno ime
      "</Punomocnik>" +
      "</Podaci_o_punomocniku>" +

      "<Dostavljanje>" +
      "<Adresa_za_dostavljanje>" +
      "<Ulica></Ulica>" +
      "<Broj_u_ulici></Broj_u_ulici>" +
      "<Postanski_broj></Postanski_broj>" +
      "<Mesto></Mesto>" +
      "<Drzava></Drzava>" +
      "</Adresa_za_dostavljanje>" +
      "<Nacin_dostave></Nacin_dostave>" +
      "</Dostavljanje>" +
      "<Podaci_o_prijavi>" +
      "<Vrsta_prijave></Vrsta_prijave>" +
      "<Broj_osnovne_prijave></Broj_osnovne_prijave>" +
      "<Datum_podnosenja_prijave></Datum_podnosenja_prijave>" +
      "</Podaci_o_prijavi>" +
      "<Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava></Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava>" +
      "</Sadrzaj_zahteva_za_priznanje_patenta>" +
      "</Zahtev_za_priznanje_patenta>";
    const editor = document.getElementById("patent");
    // Xonomy.setMode("laic")

    let docSpec = {
      onchange: function () {
        console.log("Change")
      },
      validate: function (jsElement: any) {
        // if (jsElement.value)

        if (jsElement.name == "Drzava") {
          console.log(jsElement.children[0])
        }
        for (var i = 0; i < jsElement.children.length; i++) {
          var jsChild = jsElement.children[i];
          if (jsChild.type == "element") { //if element
            docSpec.validate(jsChild); //recursion
          } else if (jsChild.type == "text") {
            if (jsChild.value == "") {
              console.log("STIGAO")
              Xonomy.warnings.push({
                  // htmlID: jsAttribute.htmlID,
                  text: "This attribute must not be empty."
                }
              );

            }
          }
        }
      },
      elements: {
        "Naziv_pronalaska": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Podnosilac_prijave_je_i_pronalazac": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        },
        "Podnosilac_prijave": {
          menu: [{
            caption: "Postavi na <FIZICKO_LICE>",
            action: Xonomy.newElementChild,
            actionParameter: "<ImePrezime><Ime></Ime><Prezime></Prezime></ImePrezime>",
            // actionParameter: {name: "tip_korisnika", value: "FIZICKO_LICE"},
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Poslovno_ime") || jsElement.hasChildElement("ImePrezime");
            }
          },
            {
              caption: "Postavi na <PRAVNO_LICE>",
              action: Xonomy.newElementChild,
              actionParameter: "<Poslovno_ime></Poslovno_ime>",
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("Poslovno_ime") || jsElement.hasChildElement("ImePrezime");
              }
            },
          ]
        },
        "Punomocnik": {
          menu: [{
            caption: "Postavi na <FIZICKO_LICE>",
            action: Xonomy.newElementChild,
            actionParameter: "<ImePrezime><Ime></Ime><Prezime></Prezime></ImePrezime>",
            // actionParameter: {name: "tip_korisnika", value: "FIZICKO_LICE"},
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Poslovno_ime") || jsElement.hasChildElement("ImePrezime");
            }
          },
            {
              caption: "Postavi na <PRAVNO_LICE>",
              action: Xonomy.newElementChild,
              actionParameter: "<Poslovno_ime></Poslovno_ime>",
              hideIf: function (jsElement: any) {
                return jsElement.hasChildElement("Poslovno_ime") || jsElement.hasChildElement("ImePrezime");
              }
            },
          ]
        },
        "Ulica": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Broj_u_ulici": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Postanski_broj": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Mesto": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Drzava": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Broj_telefona": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Broj_faksa": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "E_posta": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Ime": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Prezime": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Poslovno_ime": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Drzavljanstvo": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Pronalazac_ne_zeli_da_bude_naveden": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        }, "Vrsta_punomocnika": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["PUNOMOCNIK_ZA_ZASTUPANJE", "PUNOMOCNIK_ZA_PRIJEM_PISMENA"],
        }, "Zajednicki_predstavnik": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["true", "false"],
        }, "Nacin_dostave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["ELEKTRONSKIM_PUTEM", "PAPIRNA_FORMA"],
        }, "Vrsta_prijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askPicklist,
          askerParameter: ["DOPUNSKA", "IZDVOJENA"],
        }, "Broj_osnovne_prijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Datum_podnosenja_prijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString,
        }, "Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava": {
          menu: [{
            caption: "Dodaj <Ranija_prijava>",
            action: Xonomy.newElementChild,
            actionParameter: "<Ranija_prijava/>",
          }]
        }, "Ranija_prijava": {
          menu: [{
            caption: "Ukloni <Ranija_prijava>",
            action: Xonomy.deleteElement,
            actionParameter: "<Ranija_prijava/>",
          }, {
            caption: "Dodaj <Broj_ranije_prijave>",
            action: Xonomy.newElementChild,
            actionParameter: "<Broj_ranije_prijave/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Broj_ranije_prijave");
            }
          }, {
            caption: "Dodaj <Datum_podnosenja_prijave>",
            action: Xonomy.newElementChild,
            actionParameter: "<Datum_podnosenja_prijave/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Datum_podnosenja_prijave");
            }
          }, {
            caption: "Dodaj <Dvoslovna_oznaka>",
            action: Xonomy.newElementChild,
            actionParameter: "<Dvoslovna_oznaka/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Dvoslovna_oznaka");
            }
          }]
        }, "Broj_ranije_prijave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Datum_podnosenja_priave": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        }, "Dvoslovna_oznaka": {
          hasText: true,
          oneliner: true,
          asker: Xonomy.askString
        },
        "Podaci_o_pronalazacu": {
          menu: [{
            caption: "Ukloni <Podaci_o_pronalazacu>",
            action: Xonomy.deleteElement,
            actionParameter: "<Podaci_o_pronalazacu/>",
          }, {
            caption: "Dodaj <Pronalazac_ne_zeli_da_bude_naveden>",
            action: Xonomy.newElementChild,
            actionParameter: "<Pronalazac_ne_zeli_da_bude_naveden/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Pronalazac_ne_zeli_da_bude_naveden");
            }
          }, {
            caption: "Dodaj <Pronalazac>",
            action: Xonomy.newElementChild,
            actionParameter: "<Pronalazac/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Pronalazac");
            }
          }]
        },
        "Adresa_lica": {
          menu: [{
            caption: "Dodaj <Ulica>",
            action: Xonomy.newElementChild,
            actionParameter: "<Ulica/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Ulica");
            }
          }, {
            caption: "Dodaj <Broj_u_ulici>",
            action: Xonomy.newElementChild,
            actionParameter: "<Broj_u_ulici/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Broj_u_ulici");
            }
          }, {
            caption: "Dodaj <Postanski_broj>",
            action: Xonomy.newElementChild,
            actionParameter: "<Postanski_broj/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Postanski_broj");
            }
          }, {
            caption: "Dodaj <Mesto>",
            action: Xonomy.newElementChild,
            actionParameter: "<Mesto/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Mesto");
            }
          }, {
            caption: "Dodaj <Drzava>",
            action: Xonomy.newElementChild,
            actionParameter: "<Drzava/>",
            hideIf: function (jsElement: any) {
              return jsElement.hasChildElement("Drzava");
            }
          }]
        }
      }
    }

    Xonomy.render(xml, editor, docSpec);
  }


  addPrilogPodnosioc(event: any) {
    this.prilogPodnosioc = event.target.files[0];
  }

  addPrilogPravoPrijave(event: any) {
    this.prilogPravoPriajve = event.target.files[0];
  }

  uploadPrilogsForkJoin(brojPrijave: string) {
    if (undefined != this.prilogPodnosioc)
      this.patentService.postPrilog(brojPrijave, "Podnosioc", this.prilogPodnosioc).subscribe(() => {
        this.patentService.postPrilog(brojPrijave, "PravoPriajve", this.prilogPravoPriajve).subscribe()
        this.patentService.saveAfterPrilogAddition(brojPrijave).subscribe(() => {
          this._snackBar.open("Vaš zahtev je uspešno podnet.", '', {
            duration: 3000,
            panelClass: ['snack-bar']
          })
        });
      });
    // if (undefined != this.prilogPravoPriajve)
    //   this.patentService.postPrilog(brojPrijave, "PravoPriajve", this.prilogPravoPriajve).subscribe()
    this.patentService.saveAfterPrilogAddition(brojPrijave).subscribe(() => {
      this._snackBar.open("Vaš zahtev je uspešno podnet.", '', {
        duration: 3000,
        panelClass: ['snack-bar']
      })
    });
  }
}
