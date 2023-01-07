export function mapCreatePatent(req: String) {
  req = req.replaceAll("Sadrzaj_zahteva_za_priznanje_patenta", "sadrzajZahtevaZaPriznanjePatenta")
    // .replaceAll("Naziv_pronalaska", "nazivPronalaska")
    // .replaceAll("")
    .replaceAll("Podaci_o_podnosiocu_prijave", "podaciOPodnosiocuPrijave")
    .replaceAll("Podnosilac_prijave_je_i_pronalazac", "podnosilacPrijaveJeIPronalazac")
    .replaceAll("Podnosilac_prijave", "podnosilacPrijave")
    .replaceAll("Adresa_lica", "adresaLica")
    .replaceAll("Ulica", "ulica")
    .replaceAll("Broj_u_ulici", "brojUUlici")
    .replaceAll("Postanski_broj", "postanskiBroj")
    .replaceAll("Drzava", "drzava")
    .replaceAll("Mesto", "mesto")
    .replaceAll("Broj_telefona", "brojTelefona")
    .replaceAll("Broj_faksa", "brojFaksa")
    .replaceAll("E_posta", "email")
    .replaceAll("Drzavljanstvo", "drzavljanstvo")
    .replaceAll("Podaci_o_pronalazacu", "podaciOPronalazacu")
    .replaceAll("Pronalazac_ne_zeli_da_bude_naveden", "pronalazacNeZeliDaBudeNaveden")
    .replaceAll("<Pronalazac>", "<pronalazac>")
    .replaceAll("</Pronalazac>", "</pronalazac>")
    .replaceAll("Ime", "ime")
    .replaceAll("Prezime", "prezime")
    // .replaceAll("Poslovno ime")
    .replaceAll("Podaci_o_punomocniku", "podaciOPunomocniku")
    .replaceAll("Vrsta_punomocnika", "vrstaPunomocnika")
    .replaceAll("Zajednicki_predstavnik", "zajednickiPredstavnik")
    .replaceAll("<Punomocnik", "<punomocnik")
    .replaceAll("</Punomocnik>", "</punomocnik>")
    .replaceAll("Dostavljanje", "dostavljanje")
    .replaceAll("Adresa_za_dostavljanje", "adresaZaDostavljanje")
    .replaceAll("Nacin_dostave", "nacinDostave")
    .replaceAll("Podaci_o_prijavi", "podaciOPrijavi")
    .replaceAll("Vrsta_prijave", "vrstaPrijave")
    .replaceAll("Broj_osnovne_prijave", "brojOsnovnePrijave")
    .replaceAll("Datum_podnosenja_prijave", "datumPodnosenjaPrijave")
    .replaceAll("Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava", "zahtevZaPriznanjePrvenstvaIzRanijihPrijava")
    .replaceAll("Broj_ranije_prijave", "brojRanijePrijave")
    .replaceAll("Dvoslovna_oznaka", "dvoslovnaOznaka")
    .replaceAll("<Naziv_pronalaska Jezik='SRPSKI' xml:space='preserve'>", "<nazivSrpski>")
    .replace("</Naziv_pronalaska>", "</nazivSrpski>")
    .replaceAll("<Naziv_pronalaska Jezik='ENGLESKI' xml:space='preserve'>", "<nazivEngleski>")
    .replace("</Naziv_pronalaska>", "</nazivEngleski>")
    .replaceAll("<imeprezime>", "")
    .replaceAll("</imeprezime>", "")
    .replaceAll("Poslovno_ime", "poslovnoIme")

  // let podnosilac = req.split("<ime_ili_poslovno_ime xml:space='preserve'>")[1].split("</ime_ili_poslovno_ime>")[0]
  // let punomocnik = req.split("<ime_ili_poslovno_ime xml:space='preserve'>")[2].split("</ime_ili_poslovno_ime>")[0]
  //

  // if ()


  if (req.includes("podnosilacPrijave tip_korisnika='FIZICKO_LICE'")) {

    // req.replace("<ime_ili_poslovno_ime xml:space='preserve'>", "<ime>" + podnosilac.split("\\s+")[0])
  }
  // } else if (req.includes("podnosilacPrijave tip_korisnika='PRAVNO_LICE'")) {
  //   req.replace("<ime_ili_poslovno_ime xml:space='preserve'>", "<poslovnoIme>")
  //   req.replace("</ime_ili_poslovno_ime>", "</poslovnoIme>")
  // }
  // if (req.includes("punomocnik tip_korisnika='FIZICKO_LICE'")) {
  //
  // } else if (req.includes("punomocnik tip_korisnika='PRAVNO_LICE'")) {
  //   req.replace("<ime_ili_poslovno_ime xml:space='preserve'>", "<poslovnoIme>")
  //   req.replace("</ime_ili_poslovno_ime>", "</poslovnoIme>")
  // }
  return req
}
