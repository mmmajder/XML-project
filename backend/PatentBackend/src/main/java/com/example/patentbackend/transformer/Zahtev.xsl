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
                    width: 95%;
                    border: 1;
                    }
                    th, td {
                    text-align: left;
                    padding: 3px;
                    border: 1;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                    font-family: sans-serif;
                    }
                    tr { border: 1; }

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
                <table>
                    <tr>
                        <td colspan="2" style="text-align:center;">Popunjava zavod</td>
                    </tr>
                    <tr>
                        <td>Broj prijave</td>
                        <td><xsl:value-of select="//pat:Broj_prijave"/></td>
                    </tr>
                    <tr>
                        <td>Datum prijema</td>
                        <td><xsl:value-of select="//pat:Datum_prijema"/></td>
                    </tr>
                    <tr>
                        <td>Priznati datum podnosenja</td>
                        <td><xsl:value-of select="//pat:Priznati_datum_podnosenja"/></td>
                    </tr>
                    <tr>
                        <td>Pecat i potpis</td>
                        <td></td>
                    </tr>
                </table>
                <p><br/>
                    Republika Srbija<br/>
                    Zavod za intelektualnu svojinu<br/>
                    Kneginje Ljubice broj 5<br/>
                    11000 Beograd
                </p>
                <center>
                    <h3>ZAHTEV<br/>
                        ZA PRIZNANJE PATENTA</h3>
                </center>

                <table>
                    <tr><th colspan="7">Polje broj I NAZIV PRONALASKA</th></tr>
                    <tr>
                        <td colspan="7">Na srpskom jeziku: <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='SRPSKI']/@Naziv"/><br/>
                            Na engleskom jeziku: <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='ENGLESKI']/@Naziv"/></td>
                    </tr>
                    <tr><th colspan="7">Polje broj II PODNOSILAC PRIJAVE</th></tr>
                    <tr>
                        <td colspan="7">Podnosilac prijave je i pronalazac:
                            <xsl:if test="//pat:Podnosilac_prijave_je_i_pronalazac=true()">
                                DA
                            </xsl:if>
                            <xsl:if test="//pat:Podnosilac_prijave_je_i_pronalazac=false()">
                                NE
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Ime">
                            <th>Ime</th>
                            <th>Prezime</th>
                        </xsl:if>
                        <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime">
                            <th colspan="2">Poslovno ime</th>
                        </xsl:if>
                        <th>Ulica</th>
                        <th>Broj</th>
                        <th>Postanski broj</th>
                        <th>Mesto</th>
                        <th>Drzava</th>
                    </tr>
                    <tr>
                        <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Ime">
                            <td><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Ime"/></td>
                            <td><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Prezime"/></td>
                        </xsl:if>
                        <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime">
                            <td colspan="2"><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime"/></td>
                        </xsl:if>
                        <td><xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Ulica"/></td>
                        <td><xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Broj_u_ulici"/></td>
                        <td><xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Postanski_broj"/></td>
                        <td><xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Mesto"/></td>
                        <td><xsl:value-of select="//pat:Podnosilac_prijave//pat:Adresa_lica//pat:Drzava"/></td>
                    </tr>
                    <xsl:if test="//pat:Podaci_o_podnosiocu_prijave//pat:Ime">
                        <tr>
                            <td colspan="7">Drzavljanstvo: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Drzavljanstvo"/></td>
                        </tr>
                    </xsl:if>
                    <tr>
                        <th colspan="2">Telefon</th>
                        <th colspan="3">Broj faksa</th>
                        <th colspan="2">E-posta</th>
                    </tr>
                    <tr>
                        <td colspan="2"><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_telefona"/></td>
                        <td colspan="3"><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_faksa"/></td>
                        <td colspan="2"><xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:E_posta"/></td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr><th colspan="7">Polje broj III PRONALAZAC</th></tr>
                    <tr>
                        <td colspan="7">Pronalazac ne zeli da bude naveden u prijavi:
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
                            <th>Ime</th>
                            <th>Prezime</th>
                            <th>Ulica</th>
                            <th>Broj</th>
                            <th>Postanski broj</th>
                            <th>Mesto</th>
                            <th>Drzava</th>
                        </tr>
                        <tr>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Ime"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Prezime"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Ulica"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Broj_u_ulici"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Postanski_broj"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Mesto"/></td>
                            <td><xsl:value-of select="//pat:Pronalazac//pat:Adresa_lica//pat:Drzava"/></td>
                        </tr>
                        <tr>
                            <th colspan="2">Telefon</th>
                            <th colspan="3">Broj faksa</th>
                            <th colspan="2">E-posta</th>
                        </tr>
                        <tr>
                            <td colspan="2"><xsl:value-of select="//pat:Pronalazac//pat:Broj_telefona"/></td>
                            <td colspan="3"><xsl:value-of select="//pat:Pronalazac//pat:Broj_faksa"/></td>
                            <td colspan="2"><xsl:value-of select="//pat:Pronalazac//pat:E_posta"/></td>
                        </tr>
                    </xsl:if>
                </table>

                <br/>

                <table>
                    <tr><th colspan="7">Polje broj IV
                        <xsl:if test="//pat:Vrsta_punomocnika[text()='PUNOMOCNIK_ZA_ZASTUPANJE']">
                            PUNOMOCNK ZA ZASTUPANJE
                        </xsl:if>
                        <xsl:if test="//pat:Vrsta_punomocnika[text()='PUNOMOCNIK_ZA_PRIJEM_PISMENA']">
                            PUNOMOCNK ZA PRIJEM PISMENA
                        </xsl:if>
                    </th>
                    </tr>
                    <tr>
                        <td colspan="7">ZAJEDNICKI PREDSTAVNIK:
                            <xsl:if test="//pat:Zajednicki_predstavnik=true()">
                                DA
                            </xsl:if>
                            <xsl:if test="//pat:Zajednicki_predstavnik=false()">
                                NE
                            </xsl:if>
                        </td>
                    </tr>
                    <tr>
                        <xsl:if test="//pat:Punomocnik//pat:Ime">
                            <th>Ime</th>
                            <th>Prezime</th>
                        </xsl:if>
                        <xsl:if test="//pat:Punomocnik//pat:Poslovno_ime">
                            <th colspan="2">Poslovno ime</th>
                        </xsl:if>
                        <th>Ulica</th>
                        <th>Broj</th>
                        <th>Postanski broj</th>
                        <th>Mesto</th>
                        <th>Drzava</th>
                    </tr>
                    <tr>
                        <xsl:if test="//pat:Punomocnik//pat:Ime">
                            <td><xsl:value-of select="//pat:Punomocnik//pat:Ime"/></td>
                            <td><xsl:value-of select="//pat:Punomocnik//pat:Prezime"/></td>
                        </xsl:if>
                        <xsl:if test="//pat:Punomocnik//pat:Poslovno_ime">
                            <td colspan="2"><xsl:value-of select="//pat:Punomocnik//pat:Poslovno_ime"/></td>
                        </xsl:if>
                        <td><xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Ulica"/></td>
                        <td><xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Broj_u_ulici"/></td>
                        <td><xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Postanski_broj"/></td>
                        <td><xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Mesto"/></td>
                        <td><xsl:value-of select="//pat:Punomocnik//pat:Adresa_lica//pat:Drzava"/></td>
                    </tr>
                    <tr>
                        <th colspan="3">Telefon</th>
                        <th colspan="4">E-posta</th>
                    </tr>
                    <tr>
                        <td colspan="3"><xsl:value-of select="//pat:Punomocnik//pat:Broj_telefona"/></td>
                        <td colspan="4"><xsl:value-of select="//pat:Punomocnik//pat:E_posta"/></td>
                    </tr>

                    <xsl:if test="//pat:Adresa_za_dostavljanje">
                        <tr><th colspan="7">Polje broj V ADRESA ZA DOSTAVLJANJA</th></tr>
                        <tr>
                            <th colspan="3">Ulica</th>
                            <th>Broj</th>
                            <th>Postanski broj</th>
                            <th colspan="2">Mesto</th>
                        </tr>
                        <tr>
                            <td colspan="3"><xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Ulica"/></td>
                            <td><xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Broj_u_ulici"/></td>
                            <td ><xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Postanski_broj"/></td>
                            <td colspan="2"><xsl:value-of select="//pat:Adresa_za_dostavljanje//pat:Mesto"/></td>
                        </tr>
                    </xsl:if>

                    <tr><th colspan="7">Polje broj VI NACIN DOSTAVLJANJA</th></tr>
                    <tr>
                        <td colspan="7">
                            <xsl:if test="//pat:Nacin_dostave[text()='ELEKTRONSKIM_PUTEM']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena iskljucivo elektronskim putem u formi elektronskog dokumenta
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORM']"> <!-- postoji typo u testnom primeru -->
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                            <xsl:if test="//pat:Nacin_dostave[text()='PAPIRNA_FORMA']">
                                Podnosilac prijave je saglasan da Zavod vrsi dostavljanje pismena u papirnoj formi
                            </xsl:if>
                        </td>
                    </tr>

                    <tr><th colspan="7">Polje broj VII
                        <xsl:if test="//pat:Vrsta_prijave[text()='DOPUNSKA']">
                            DOPUNSKA PRIJAVA
                        </xsl:if>
                        <xsl:if test="//pat:Vrsta_prijave[text()='IZDVOJENA']"> <!-- postoji typo u testnom primeru -->
                            IZDVOJENA PRIJAVA
                        </xsl:if>
                    </th></tr>
                    <tr>
                        <td colspan="7">
                            Broj prvobitne prijave / osnovne prijave, odnosno osnovnog patenta: <xsl:value-of select="//pat:Broj_osnovne_prijave"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="7">
                            Datum podnosenja prvobitne prijave / osnovne prijave: <xsl:value-of select="//pat:Datum_podnosenja_prijave"/>
                        </td>
                    </tr>

                    <tr><th colspan="7">Polje broj VIII ZAHTEV ZA PRIZNANJE PRAVA PRVENSTVA IZ RANIJIH PRIJAVA</th></tr>
                    <tr>
                        <th colspan="2">
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
                                <td colspan="2">
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
