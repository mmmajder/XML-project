<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:aut="http://www.ftn.uns.ac.rs/autorskoDelo" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</title>
                <style type="text/css">
                    body {
                    font-family: Arial, Helvetica, sans-serif;
                    }
                    table {
                    margin-left: auto;
                    margin-right: auto;
                    width: 80%;
                    }
                    .shifted-top-l {
                    margin-top: 45px;
                    }
                    .shifted-top-sm {
                    margin-top: 20px;
                    }
                    .shifted-left {
                    margin-left: 0;
                    }
                    td {
                    padding-left: 10px;
                    font-size: 16px;
                    padding-top: 3px;
                    padding-bottom: 3px;
                    }
                    th {
                    font-size: 16px;
                    }
                    td.name {
                    text-align: center;
                    font-size: 18px;
                    }
                    table, td, th {
                    border: 1px solid black;
                    border-collapse: collapse;
                    }
                    td:hover {
                    font-style: italic;
                    background-color: #cbd4f2;
                    }
                    .centered {
                    text-align: center
                    }
                </style>
            </head>
            <body>
                <div style="width: 95%; border: 1px solid black; margin: auto">
                    <div style="border-bottom: 1px solid black; padding: 5px">
                        <div style="width: 100%; margin-left: 10px">
                            <div style="display: inline-block; width: 70%">
                                <h3>ZAVOD ZA INTELEKTUALNU SVOJINU</h3>
                                <h4>Beograd, Kneginje Ljubice 5</h4>
                            </div>
                            <div style="text-align: right; display: inline-block;">
                                <h3>OBRAZAC A-1</h3>
                            </div>
                        </div>
                        <h3 style="text-align: center; padding-bottom: 30px">ZAHTEV ZA UNOŠENJE U EVIDENCIJU I
                            DEPONOVANJE AUTORSKIH DELA
                        </h3>
                    </div>

                    <table style="padding: 10px">
                        <tr>
                            <td style="border-left-width:1px;">
                                Podnosilac
                                <br/>
                                <xsl:if test="//aut:Podnosilac_zahteva//aut:Ime">
                                    <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Ime"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Prezime"/>,
                                    <xsl:text>&#x20;</xsl:text>
                                    <br/>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Ulica"/>
                                    <xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Broj_u_ulici"/>,
                                    <xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Adresa//aut:Drzava"/>
                                    <br/>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Drzavljanstvo"/>
                                </xsl:if>
                                <xsl:if test="//aut:Podnosilac_zahteva//aut:Poslovno_ime">
                                    <i>
                                        <xsl:value-of
                                                select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Poslovno_ime"/>
                                    </i>
                                    ,
                                    <xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Sediste"/>
                                </xsl:if>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td style="border-left-width:1px;">
                                telefon:
                                <xsl:value-of
                                        select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Broj_telefona"/>
                            </td>
                            <td>
                                e-mail:
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Email"/>
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
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Prezime"/>,
                                    <xsl:text>&#x20;</xsl:text>
                                    <br/>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Ulica"/>
                                    <xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Broj_u_ulici"/>,
                                    <xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                                    <xsl:value-of
                                            select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Adresa//aut:Drzava"/>
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
                                <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Prezime"/>,
                                <xsl:text>&#x20;</xsl:text>
                                <br/>
                                <xsl:value-of
                                        select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Ulica"/>
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of
                                        select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Broj_u_ulici"/>,
                                <xsl:text>&#x20;</xsl:text>
                                <xsl:value-of
                                        select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of
                                        select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Mesto"/><xsl:text>&#x20;</xsl:text>
                                <xsl:value-of
                                        select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Drzava"/>
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

                    <br/>

                    <table style="width:30%">
                        <tr>
                            <td style="border-top-width:1px; border-left-width:1px;">Prilozi uz prijavu</td>
                        </tr>
                        <tr>
                            <td style="border-left-width:1px;">
                                <xsl:element name="a">
                                    <xsl:attribute name="href">
                                        http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of
                                            select="//aut:PutanjaDoOpisa"/>
                                    </xsl:attribute>
                                    Opis autorskog dela
                                </xsl:element>
                            </td>
                        </tr>
                        <tr>
                            <td style="border-left-width:1px;">
                                <xsl:element name="a">
                                    <xsl:attribute name="href">
                                        http://localhost:8001/autorskaPrava/download/prilog/<xsl:value-of
                                            select="//aut:PutanjaDoPrimera"/>
                                    </xsl:attribute>
                                    Primerak autorskog dela
                                </xsl:element>
                            </td>
                        </tr>
                    </table>

                    <table style="margin: 80px 20px 20px auto; width: 40%;">
                        <tbody>
                            <tr>
                                <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Broj prijave
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size: 22px; font-weight: bold; padding: 5px 0 15px 15px; border-top: white">
                                    A -
                                    <xsl:value-of select="//aut:Broj_prijave"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size: 20px; padding: 15px 0 5px 15px; border-bottom: white">Datum
                                    podnošenja:
                                </td>
                            </tr>
                            <tr>
                                <td style="font-size: 22px; padding: 5px 0 15px 15px; border-top: white">
                                    <xsl:value-of select="substring(//aut:Datum_podnosenja, 0, 11)"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
