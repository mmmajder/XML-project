<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:aut="http://www.ftn.uns.ac.rs/autorskoDelo" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</title>
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
                <center>
                    <h3>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</h3>
                    <h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>
                </center>

                <table>
                    <tr>
                        <th colspan="6">1) Podnosilac zahteva</th>
                    </tr>
                    <tr>
                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">
                            <th colspan="2">Ime</th>
                            <th colspan="2">Prezime</th>
                            <th colspan="2">Državljanstvo</th>
                        </xsl:if>
                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">
                            <th colspan="3">Poslovno ime</th>
                            <th colspan="3">Sedište</th>
                        </xsl:if>
                    </tr>
                    <tr>
                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">
                            <td colspan="2">
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Ime"/>
                            </td>
                            <td colspan="2">
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Prezime"/>
                            </td>
                            <td colspan="2">
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Drzavljanstvo"/>
                            </td>
                        </xsl:if>
                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">
                            <td colspan="3">
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Poslovno_ime"/>
                            </td>
                            <td colspan="3">
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Sediste"/>
                            </td>
                        </xsl:if>
                    </tr>
                    <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">
                        <tr>
                            <th>Ulica</th>
                            <th>Broj</th>
                            <th>Poštanski broj</th>
                            <th>Mesto</th>
                            <th>Država</th>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Ulica"/>
                            </td>
                            <td>
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Broj"/>
                            </td>
                            <td>
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Postanski_broj"/>
                            </td>
                            <td>
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Mesto"/>
                            </td>
                            <td>
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Drzava"/>
                            </td>
                        </tr>
                    </xsl:if>

                    <tr>
                        <th>Telefon</th>
                        <th>Email</th>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Telefon"/>
                        </td>
                        <td colspan="3">
                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>
                        </td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr>
                        <th colspan="5">2) Podaci o autoru</th>
                    </tr>
                    <tr>
                        <th colspan="2">Ime</th>
                        <th colspan="2">Prezime</th>
                        <th>Državljanstvo</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Ime"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Prezime"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Drzavljanstvo"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Ulica</th>
                        <th>Broj</th>
                        <th>Poštanski broj</th>
                        <th>Mesto</th>
                        <th>Država</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Ulica"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Broj"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Postanski_broj"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Mesto"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Drzava"/>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">Telefon</th>
                        <th colspan="3">Email</th>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Telefon"/>
                        </td>
                        <td colspan="3">
                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="5">Pseudonim/Znak autora ako ga ima</th>
                    </tr>
                    <tr>
                        <td colspan="5">
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Pseudonim"/>
                        </td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr>
                        <th colspan="2">3) Podaci o punomoćniku</th>
                    </tr>
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime"/>
                        </td>
                        <td>
                            <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Prezime"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Ulica</th>
                        <th>Broj</th>
                        <th>Poštanski broj</th>
                        <th>Mesto</th>
                        <th>Država</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of
                                    select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Ulica"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Broj"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Postanski_broj"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Mesto"/>
                        </td>
                        <td>
                            <xsl:value-of
                                    select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Drzava"/>
                        </td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr>
                        <th colspan="2">4) Podaci o autorskom delu</th>
                    </tr>
                    <tr>
                        <th>Naslov autorskog dela</th>
                        <th>Vrsta autorskog dela</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Naslov_autorskog_dela"/>
                        </td>
                        <td>
                            <xsl:value-of select="aut:Autorsko_delo//aut:Vrsta_autorskog_dela"/>
                        </td>
                    </tr>

                    <tr>
                        <th>Forma zapisa autorskog dela</th>
                        <th>Način korišćenja ili planirani način korišćenja</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Forma_zapisa_autorskog_dela"/>
                        </td>
                        <td>
                            <xsl:value-of select="aut:Autorsko_delo//aut:Nacin_koriscenja_autorskog_dela"/>
                        </td>
                    </tr>
                    <xsl:if test="//aut:Autorsko_delo//aut:Zasnivano_delo">
                        <tr>
                            <th>Naslov zasnivanog autorskog dela</th>
                            <th>Autor zasnivanog autorskog dela</th>
                        </tr>
                        <tr>
                            <td>
                                <xsl:value-of select="//aut:Autorsko_delo//aut:Zasnivano_delo//naslov"/>
                            </td>
                            <td>
                                <xsl:value-of select="aut:Autorsko_delo//aut:Zasnivano_delo//autor"/>
                            </td>
                        </tr>
                    </xsl:if>

                    <tr>
                        <td>Autorsko delo je stvoreno u radnom odnosu.</td>
                        <td>
                            <xsl:value-of select="aut:Autorsko_delo//aut:Autorsko_delo_stvoreno_u_radnom_odnosu"/>
                        </td>
                    </tr>
                </table>

                <br/>

                <table>
                    <tr>Informacije o zahtevu</tr>
                    <tr>
                        <th>Broj prijave:</th>
                        <th>Datum podnošenja:</th>
                    </tr>
                    <tr>
                        <td>
                            <xsl:value-of select="//aut:Broj_prijave"/>
                        </td>
                        <td>
                            <xsl:value-of select="//aut:Datum_podnosenja"/>
                        </td>
                    </tr>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
