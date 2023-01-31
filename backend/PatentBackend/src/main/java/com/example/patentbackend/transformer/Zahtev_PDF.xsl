<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:pat="http://www.ftn.uns.ac.rs/patent" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Patent</title>
                <style type="text/css">
                    table {
                    font-family: serif;
                    border-collapse: collapse;
                    margin: 0;
                    width: 100%;
                    }
                    th, td {
                    text-align: left;
                    padding: 3px;
                    border-bottom-width:1px;
                    border-right-width:1px;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                    font-family: sans-serif;
                    }

                    .leftBorder{
                    border-left-width:1px;
                    }

                    .topBorder{
                    border-top-width:1px;
                    }

                    body { font-family: sans-serif; }
                    p { text-indent: 30px; }
                    .sup {
                    vertical-align: super;
                    padding-left: 4px;
                    font-size: small;
                    text-transform: lowercase;
                    }

                </style>
            </head>
            <body>
                <table style="width: 65%">
                    <tr>
                        <td colspan="2" style="text-align:center; border-top-width:1px; border-left-width:1px;">
                            Popunjava zavod
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">Broj prijave</td>
                        <td>
                            <xsl:value-of select="//pat:Broj_prijave"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">Datum prijema</td>
                        <td>
                            <xsl:value-of select="//pat:Datum_prijema"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">Priznati datum podnosenja</td>
                        <td>
                            <xsl:value-of select="//pat:Priznati_datum_podnosenja"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">Pecat i potpis</td>
                        <td></td>
                    </tr>
                </table>
                <p>
                    <br/>
                    Republika Srbija
                    <br/>
                    Zavod za intelektualnu svojinu
                    <br/>
                    Kneginje Ljubice broj 5
                    <br/>
                    11000 Beograd
                </p>
                <center>
                    <h3>ZAHTEV
                        <br/>
                        ZA PRIZNANJE PATENTA
                    </h3>
                </center>

                <!--                <tr><td colspan="2" style="border-left-width:1px;">-->
                <!--                    <b>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</b> <br/>-->
                <!--                    <xsl:for-each select="//sz:Boja">-->
                <!--                        <xsl:value-of select="."/>, <xsl:text>&#x20;</xsl:text>-->
                <!--                    </xsl:for-each>-->
                <!--                </td></tr>-->

                <table>
                    <tr>
                        <th colspan="3" style="border-top-width:1px; border-left-width:1px;">Polje broj I NAZIV
                            PRONALASKA
                        </th>
                    </tr>
                    <tr>
                        <td colspan="3" style="border-left-width:1px;">Na srpskom jeziku:
                            <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='SRPSKI']/@Naziv"/>
                            <br/>
                            Na engleskom jeziku:
                            <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='ENGLESKI']/@Naziv"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" style="border-left-width:1px;">
                            <b>Polje broj II PODNOSILAC PRIJAVE</b>
                            Podnosilac prijave je i pronalazac:
                            <xsl:if test="//pat:Podnosilac_prijave_je_i_pronalazac=true()">
                                DA
                            </xsl:if>
                            <xsl:if test="//pat:Podnosilac_prijave_je_i_pronalazac=false()">
                                NE
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="3" style="border-left-width:1px;">
                            <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Ime">
                                Ime i Prezime
                            </xsl:if>
                            <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime">
                                Poslovno ime
                            </xsl:if>
                            <br/>
                            <br/>
                            <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Ime">
                                <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Ime"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Prezime"/>
                            </xsl:if>
                            <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime">
                                <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime"/>
                            </xsl:if>
                        </td>
                        <td rowspan="3">
                            Ulica i broj, poštanski broj, mesto i država
                            <br/>
                            <br/>
                            <xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Ulica"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Broj_u_ulici"/>
                            <xsl:text>&#x20;</xsl:text>,
                            <xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Postanski_broj"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Mesto"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Drzava"/>
                        </td>
                        <td>
                            Broj telefona
                            <br/>
                            <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_telefona"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Broj faksa
                            <br/>
                            <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_faksa"/>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2">
                            E-pošta
                            <br/>
                            <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:E_posta"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="border-left-width:1px;">Drzavljanstvo:
                            <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Drzavljanstvo"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" style="border-left-width:1px;">
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of
                                        select="//pat:Podaci_o_podnosiocu_prijave//pat:Putanja_do_priloga_podnosioca"/>
                                </xsl:attribute>
                                Izjavu o zajedničkom predstavniku
                            </xsl:element>
                        </td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr>
                        <td colspan="3" style="border-top-width:1px; border-left-width:1px;">
                            <b>Polje broj III PRONALAZAC</b>
                            Pronalazac ne zeli da bude naveden u prijavi:
                            <xsl:if test="//pat:Pronalazac_ne_zeli_da_bude_naveden=true()">
                                NE ZELI
                            </xsl:if>
                            <xsl:if test="//pat:Pronalazac_ne_zeli_da_bude_naveden=false()">
                                ZELI
                            </xsl:if>
                        </td>
                    </tr>
                    <xsl:if test="//pat:Pronalazac_ne_zeli_da_bude_naveden=false()"> <!-- informacije o pronalazacu se ispisuju samo ukoliko on to zeli -->
                        <tr>
                            <td rowspan="3" style="border-left-width:1px;">
                                <xsl:if test="//pat:Pronalazac//pat:Ime">
                                    Ime i Prezime
                                </xsl:if>
                                <xsl:if test="//pat:Pronalazac//pat:Poslovno_ime">
                                    Poslovno ime
                                </xsl:if>
                                <br/>
                                <br/>
                                <xsl:if test="//pat:Pronalazac//pat:Ime">
                                    <xsl:value-of select="//pat:Pronalazac//pat:Ime"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of select="//pat:Pronalazac//pat:Prezime"/>
                                </xsl:if>
                                <xsl:if test="//pat:Pronalazac//pat:Poslovno_ime">
                                    <xsl:value-of select="//pat:Pronalazac//pat:Poslovno_ime"/>
                                </xsl:if>
                            </td>
                            <td rowspan="3">
                                Ulica i broj, poštanski broj, mesto i država
                                <br/>
                                <br/>
                                <xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Ulica"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Broj_u_ulici"/> <xsl:text>&#x20;</xsl:text>,
                                <xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Postanski_broj"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Mesto"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Drzava"/>
                            </td>
                            <td>
                                Broj telefona
                                <br/>
                                <xsl:value-of select="//pat:Pronalazac//pat:Broj_telefona"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Broj faksa
                                <br/>
                                <xsl:value-of select="//pat:Pronalazac//pat:Broj_faksa"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                E-pošta
                                <br/>
                                <xsl:value-of select="//pat:Pronalazac//pat:E_posta"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" style="border-left-width:1px;">
                                <xsl:element name="a">
                                    <xsl:attribute name="href">
                                        http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of
                                            select="//pat:Podaci_o_podnosiocu_prijave//pat:Putanja_do_primera"/>
                                    </xsl:attribute>
                                    Izjava podnosilaca prijave o osnovu sticanja prava
                                </xsl:element>
                            </td>
                        </tr>
                    </xsl:if>
                </table>

                <br/>

                <table>
                    <tr>
                        <td colspan="3" style="border-top-width:1px; border-left-width:1px;">
                            <b>Polje broj IV</b>
                            <xsl:if test="//pat:Vrsta_punomocnika[text()='PUNOMOCNIK_ZA_ZASTUPANJE']">
                                PUNOMOCNK ZA ZASTUPANJE
                            </xsl:if>
                            <xsl:if test="//pat:Vrsta_punomocnika[text()='PUNOMOCNIK_ZA_PRIJEM_PISMENA']">
                                PUNOMOCNK ZA PRIJEM PISMENA
                            </xsl:if>
                            <br/>
                            ZAJEDNICKI PREDSTAVNIK:
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:if test="//pat:Zajednicki_predstavnik=true()">
                                DA
                            </xsl:if>
                            <xsl:if test="//pat:Zajednicki_predstavnik=false()">
                                NE
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td rowspan="2" style="border-left-width:1px;">
                            <xsl:if test="//pat:Punomocnik//pat:Ime">
                                Ime i Prezime
                            </xsl:if>
                            <xsl:if test="//pat:Punomocnik//pat:Poslovno_ime">
                                Poslovno ime
                            </xsl:if>
                            <br/>
                            <br/>
                            <xsl:if test="//pat:Punomocnik//pat:Ime">
                                <xsl:value-of select="//pat:Punomocnik//pat:Ime"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Punomocnik//pat:Prezime"/>
                            </xsl:if>
                            <xsl:if test="//pat:Punomocnik//pat:Poslovno_ime">
                                <xsl:value-of select="//pat:Punomocnik//pat:Poslovno_ime"/>
                            </xsl:if>
                        </td>
                        <td rowspan="2">
                            Ulica i broj, poštanski broj, mesto i država
                            <br/>
                            <br/>
                            <xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Ulica"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Broj_u_ulici"/> <xsl:text>&#x20;</xsl:text>,
                            <xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Postanski_broj"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Mesto"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Drzava"/>
                        </td>
                        <td>
                            Broj telefona
                            <br/>
                            <xsl:value-of select="//pat:Punomocnik//pat:Broj_telefona"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            E-pošta
                            <br/>
                            <xsl:value-of select="//pat:Punomocnik//pat:E_posta"/>
                        </td>
                    </tr>
                </table>
                <xsl:if test="//pat:Adresa_za_dostavljanje">
                    <table>
                        <tr>
                            <th style="border-left-width:1px;">Polje broj V ADRESA ZA DOSTAVLJANJA</th>
                        </tr>
                        <tr>
                            <td style="border-left-width:1px;">Ulica i broj, poštanski broj i mesto:
                                <br/>
                                <br/>
                                <xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Ulica"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Broj_u_ulici"/>
                                <xsl:text>&#x20;</xsl:text>,
                                <xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Postanski_broj"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Mesto"/>
                                <xsl:text>&#x20;</xsl:text>
                            </td>
                        </tr>
                    </table>
                </xsl:if>
                <table>
                    <tr>
                        <th style="border-left-width:1px;">Polje broj VI NACIN DOSTAVLJANJA</th>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            <xsl:if test="//pat:Nacin_dostave[text()='ELEKTRONSKIM_PUTEM']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena iskljucivo
                                elektronskim putem u formi elektronskog dokumenta
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORM']"> <!-- postoji typo u testnom primeru -->
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORMA']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <th style="border-left-width:1px;">Polje broj VII
                            <xsl:if test="//pat:Vrsta_prijave[text()='DOPUNSKA']">
                                DOPUNSKA PRIJAVA
                            </xsl:if>
                            <xsl:if test="//pat:Vrsta_prijave[text()='IZDVOJENA']"> <!-- postoji typo u testnom primeru -->
                                IZDVOJENA PRIJAVA
                            </xsl:if>
                        </th>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            <xsl:if test="//pat:Nacin_dostave[text()='ELEKTRONSKIM_PUTEM']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena iskljucivo
                                elektronskim putem u formi elektronskog dokumenta
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORM']"> <!-- postoji typo u testnom primeru -->
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORMA']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta:
                            <xsl:value-of select="//pat:Broj_osnovne_prijave"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Datum podnosenja prvobitne prijave / osnovne prijave:
                            <xsl:value-of select="//pat:Datum_podnosenja_prijave"/>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th colspan="7" style="border-left-width:1px;">Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA
                            PRVENSTVA IZ RANIJIH PRIJAVA
                        </th>
                    </tr>
                    <tr>
                        <th colspan="2" style="border-left-width:1px;">
                            Datum podnosenja ranije prijave
                        </th>
                        <th colspan="3">
                            Broj ranije prijave
                        </th>
                        <th colspan="2">
                            Dvoslovna oznaka drzave, regionalne ili medjunarodne organizacije
                        </th>
                    </tr>
                    <xsl:if test="//pat:Ranija_prijava">
                        <xsl:for-each select="//pat:Ranija_prijava">
                            <tr>
                                <td colspan="2" style="border-left-width:1px;">
                                    <xsl:value-of select="pat:Broj_ranije_prijave"/>
                                </td>
                                <td colspan="3">
                                    <xsl:value-of select="pat:Datum_podnosenja_priave"/>
                                </td>
                                <td colspan="2">
                                    <xsl:value-of select="pat:Dvoslovna_oznaka"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </xsl:if>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
