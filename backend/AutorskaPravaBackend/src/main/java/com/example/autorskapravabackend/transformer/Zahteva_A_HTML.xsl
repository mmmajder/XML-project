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
                    width: 100%;
                    border:0px;
                    }
                    th, td {
                    text-align: left;
                    padding: 3px;
                    border:0px;
                    border-bottom-width:1px;
                    border-right-width:1px;
                    border-style: solid;
                    }

                    tr{
                    border:0px;
                    }

                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                    font-family: sans-serif;
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
                <!--                <center>-->
                <!--                    <h3>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</h3>-->
                <!--                    <h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>-->
                <!--                </center>-->

                <table>
                    <tr>
                        <td style="border-top-width:1px; border-left-width:1px;">
                            <b>ZAVOD ZA INTELEKTUALNU SVOJINU</b> <br/>
                            Beograd, Knjeginje Ljubice 5
                        </td>
                        <td style="text-align:center; border-top-width:0px;">
                            <b>ZAHTEV ZA UNOŠENJE U EVIDENCIJU I DEPONOVANJE AUTORSKIH DELA</b>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Podnosilac
                            <br/>
                            <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Ime"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Prezime"/>, <xsl:text>&#x20;</xsl:text>
                                <br/>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Ulica"/> <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Broj_u_ulici"/>, <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Drzava"/>
                                <br/>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Drzavljanstvo"/>
                            </xsl:if>
                            <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">
                                <i><xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Poslovno_ime"/></i>,
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Sediste"/>
                            </xsl:if>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td style="border-left-width:1px;">
                            telefon: <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Broj_telefona"/>
                        </td>
                        <td>
                            e-mail: <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td style="border-left-width:1px;">
                            <xsl:if test="//aut:Autor//aut:Podaci_o_autoru//aut:Pseudonim">
                                Pseudonim ili znak autora
                                <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Pseudonim"/>
                                <br/>
                                <br/>
                            </xsl:if>

                            <xsl:if test="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime">
                                Punomoćnik
                                <br/>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Prezime"/>, <xsl:text>&#x20;</xsl:text>
                                <br/>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Ulica"/> <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Broj_u_ulici"/>, <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Drzava"/>
                                <br/>
                                <br/>
                            </xsl:if>

                            Naslov autorskog dela
                            <br/>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Naslov_autorskog_dela"/>
                            <br/>
                            <br/>

                            Vrsta autorskog dela
                            <br/>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Vrsta_autorskog_dela"/>
                            <br/>
                            <br/>

                            Forma zapisa autorskog dela
                            <br/>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Forma_zapisa_autorskog_dela"/>
                            <br/>
                            <br/>

                            Podaci o autoru
                            <br/>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Ime"/><xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Prezime"/>, <xsl:text>&#x20;</xsl:text>
                            <br/>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Ulica"/> <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Broj_u_ulici"/>, <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Drzava"/>
                            <br/>
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Drzavljanstvo"/>
                            <br/>
                            <br/>

                            <xsl:choose>
                                <xsl:when test="aut:Autorsko_delo//aut:Autorsko_delo_stvoreno_u_radnom_odnosu">
                                    Autorsko delo je stvoreno u radnom odnosu.
                                </xsl:when>
                                <xsl:otherwise>
                                    Autorsko delo nije stvoreno u radnom odnosu.
                                </xsl:otherwise>
                            </xsl:choose>
                            <br/>
                            <br/>

                            Način korišćenja ili planirani način korišćenja
                            <br/>
                            <xsl:value-of select="//aut:Autorsko_delo//aut:Nacin_koriscenja_autorskog_dela"/>
                            <br/>
                            <br/>

                        </td>
                    </tr>
                </table>

                <!--                <table>-->
                <!--                    <tr>-->
                <!--                        <th colspan="6">1) Podnosilac zahteva</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">-->
                <!--                            <th colspan="2">Ime</th>-->
                <!--                            <th colspan="2">Prezime</th>-->
                <!--                            <th colspan="2">Državljanstvo</th>-->
                <!--                        </xsl:if>-->
                <!--                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">-->
                <!--                            <th colspan="3">Poslovno ime</th>-->
                <!--                            <th colspan="3">Sedište</th>-->
                <!--                        </xsl:if>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">-->
                <!--                            <td colspan="2">-->
                <!--                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Ime"/>-->
                <!--                            </td>-->
                <!--                            <td colspan="2">-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Prezime"/>-->
                <!--                            </td>-->
                <!--                            <td colspan="2">-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Drzavljanstvo"/>-->
                <!--                            </td>-->
                <!--                        </xsl:if>-->
                <!--                        <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">-->
                <!--                            <td colspan="3">-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Poslovno_ime"/>-->
                <!--                            </td>-->
                <!--                            <td colspan="3">-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Sediste"/>-->
                <!--                            </td>-->
                <!--                        </xsl:if>-->
                <!--                    </tr>-->
                <!--                    <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">-->
                <!--                        <tr>-->
                <!--                            <th colspan="2">Ulica</th>-->
                <!--                            <th>Broj</th>-->
                <!--                            <th>Poštanski broj</th>-->
                <!--                            <th>Mesto</th>-->
                <!--                            <th>Država</th>-->
                <!--                        </tr>-->
                <!--                        <tr>-->
                <!--                            <td colspan="2">-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Ulica"/>-->
                <!--                            </td>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Broj_u_ulici"/>-->
                <!--                            </td>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Postanski_broj"/>-->
                <!--                            </td>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Mesto"/>-->
                <!--                            </td>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of-->
                <!--                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Drzava"/>-->
                <!--                            </td>-->
                <!--                        </tr>-->
                <!--                    </xsl:if>-->

                <!--                    <tr>-->
                <!--                        <th colspan="3">Telefon</th>-->
                <!--                        <th colspan="3">Email</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td colspan="3">-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Broj_telefona"/>-->
                <!--                        </td>-->
                <!--                        <td colspan="3">-->
                <!--                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <!--                </table>-->

                <!--                <br/>-->

                <!--                <table>-->
                <!--                    <tr>-->
                <!--                        <th colspan="5">2) Podaci o autoru</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <th colspan="2">Ime</th>-->
                <!--                        <th colspan="2">Prezime</th>-->
                <!--                        <th>Državljanstvo</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td colspan="2">-->
                <!--                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Ime"/>-->
                <!--                        </td>-->
                <!--                        <td colspan="2">-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Prezime"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Drzavljanstvo"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->

                <!--                    <tr>-->
                <!--                        <th>Ulica</th>-->
                <!--                        <th>Broj</th>-->
                <!--                        <th>Poštanski broj</th>-->
                <!--                        <th>Mesto</th>-->
                <!--                        <th>Država</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Ulica"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Broj_u_ulici"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Postanski_broj"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Mesto"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Drzava"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->

                <!--                    <tr>-->
                <!--                        <th colspan="2">Telefon</th>-->
                <!--                        <th colspan="3">Email</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td colspan="2">-->
                <!--                            <xsl:value-of-->
                <!--                                    select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Broj_telefona"/>-->
                <!--                        </td>-->
                <!--                        <td colspan="3">-->
                <!--                            <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->

                <!--                    <xsl:if test="//aut:Autor//aut:Podaci_o_autoru//aut:Pseudonim">-->
                <!--                        <tr>-->
                <!--                            <th colspan="5">Pseudonim/Znak autora</th>-->
                <!--                        </tr>-->
                <!--                        <tr>-->
                <!--                            <td colspan="5">-->
                <!--                                <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Pseudonim"/>-->
                <!--                            </td>-->
                <!--                        </tr>-->
                <!--                    </xsl:if>-->
                <!--                </table>-->

                <!--                <br/>-->

                <!--                <table>-->
                <!--                    <xsl:choose>-->
                <!--                        <xsl:when test="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime">-->
                <!--                            <tr>-->
                <!--                                <th colspan="5">3) Podaci o punomocniku</th>-->
                <!--                            </tr>-->
                <!--                            <tr>-->
                <!--                                <th colspan="2">Ime</th>-->
                <!--                                <th colspan="3">Prezime</th>-->
                <!--                            </tr>-->
                <!--                            <tr>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime"/>-->
                <!--                                </td>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Prezime"/>-->
                <!--                                </td>-->
                <!--                            </tr>-->

                <!--                            <tr>-->
                <!--                                <th>Ulica</th>-->
                <!--                                <th>Broj</th>-->
                <!--                                <th>Poštanski broj</th>-->
                <!--                                <th>Mesto</th>-->
                <!--                                <th>Država</th>-->
                <!--                            </tr>-->
                <!--                            <tr>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Ulica"/>-->
                <!--                                </td>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Broj_u_ulici"/>-->
                <!--                                </td>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Postanski_broj"/>-->
                <!--                                </td>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Mesto"/>-->
                <!--                                </td>-->
                <!--                                <td>-->
                <!--                                    <xsl:value-of-->
                <!--                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Drzava"/>-->
                <!--                                </td>-->
                <!--                            </tr>-->
                <!--                        </xsl:when>-->
                <!--                        <xsl:otherwise>-->
                <!--                            <tr>-->
                <!--                                <th colspan="5">Zahtev nije podnet preko punomocnika.</th>-->
                <!--                            </tr>-->
                <!--                        </xsl:otherwise>-->
                <!--                    </xsl:choose>-->
                <!--                </table>-->

                <!--                <br/>-->

                <!--                <table>-->
                <!--                    <tr>-->
                <!--                        <th colspan="2">4) Podaci o autorskom delu</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <th>Naslov autorskog dela</th>-->
                <!--                        <th>Vrsta autorskog dela</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of select="//aut:Autorsko_delo//aut:Naslov_autorskog_dela"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of select="//aut:Autorsko_delo//aut:Vrsta_autorskog_dela"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->

                <!--                    <tr>-->
                <!--                        <th>Forma zapisa autorskog dela</th>-->
                <!--                        <th>Nacin korišcenja ili planirani nacin korišcenja</th>-->
                <!--                    </tr>-->
                <!--                    <tr>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of select="//aut:Autorsko_delo//aut:Forma_zapisa_autorskog_dela"/>-->
                <!--                        </td>-->
                <!--                        <td>-->
                <!--                            <xsl:value-of select="//aut:Autorsko_delo//aut:Nacin_koriscenja_autorskog_dela"/>-->
                <!--                        </td>-->
                <!--                    </tr>-->
                <!--                    <xsl:if test="//aut:Autorsko_delo//aut:Zasnivano_delo//naslov">-->
                <!--                        <tr>-->
                <!--                            <th>Naslov zasnivanog autorskog dela</th>-->
                <!--                            <th>Autor zasnivanog autorskog dela</th>-->
                <!--                        </tr>-->
                <!--                        <tr>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of select="//aut:Autorsko_delo//aut:Zasnivano_delo//naslov"/>-->
                <!--                            </td>-->
                <!--                            <td>-->
                <!--                                <xsl:value-of select="aut:Autorsko_delo//aut:Zasnivano_delo//autor"/>-->
                <!--                            </td>-->
                <!--                        </tr>-->
                <!--                    </xsl:if>-->

                <!--                    <xsl:choose>-->
                <!--                        <xsl:when test="aut:Autorsko_delo//aut:Autorsko_delo_stvoreno_u_radnom_odnosu">-->
                <!--                            <tr>-->
                <!--                                <th colspan="2">Autorsko delo je stvoreno u radnom odnosu.</th>-->
                <!--                            </tr>-->
                <!--                        </xsl:when>-->
                <!--                        <xsl:otherwise>-->
                <!--                            <tr>-->
                <!--                                <th colspan="2">Autorsko delo nije stvoreno u radnom odnosu.</th>-->
                <!--                            </tr>-->
                <!--                        </xsl:otherwise>-->
                <!--                    </xsl:choose>-->
                <!--                </table>-->

                <br/>

                <table style="width:30%" >
                    <tr><td style="border-top-width:1px; border-left-width:1px;">Informacije o zahtevu</td></tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Broj prijave:
                            <br/>
                            <xsl:value-of select="//aut:Broj_prijave"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            Datum podnošenja:
                            <br/>
                            <xsl:value-of select="//aut:Datum_podnosenja"/>
                        </td>
                    </tr>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
