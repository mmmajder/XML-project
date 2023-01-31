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
                <!--                <center>-->
                <!--                    <h3>Zahtev za unošenje u evidenciju i deponovanje autorskih prava</h3>-->
                <!--                    <h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>-->
                <!--                </center>-->

                <table>
                    <tr>
                        <td style="border-top-width:1px; border-left-width:1px;">
                            <b>ZAVOD ZA INTELEKTUALNU SVOJINU</b>
                            <br/>
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
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Prezime"/>,
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
                                <xsl:value-of select="//aut:Podnosilac_zahteva//aut:Podaci_o_podnosiocu//aut:Sediste"/>
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
                                <xsl:value-of select="//aut:Podaci_o_punomocniku//aut:Osnovni_licni_podaci//aut:Ime"/><xsl:text>&#x20;</xsl:text>
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
                            <xsl:value-of select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Ulica"/>
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Broj_u_ulici"/>,
                            <xsl:text>&#x20;</xsl:text>
                            <xsl:value-of
                                    select="//aut:Autor//aut:Podaci_o_autoru//aut:Lice//aut:Adresa//aut:Postanski_broj"/><xsl:text>&#x20;</xsl:text>
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
                <br/>

                <table style="width:30%">
                    <tr>
                        <td style="border-top-width:1px; border-left-width:1px;">Prilozi uz prijavu</td>
                    </tr>
                    <tr>
                        <td style="border-left-width:1px;">
                            <xsl:element name="a">
                                <xsl:attribute name="href">
                                    http://localhost:8001/download/prilog/<xsl:value-of
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
                                    http://localhost:8001/download/prilog/<xsl:value-of
                                        select="//aut:PutanjaDoPrimera"/>
                                </xsl:attribute>
                                Primerak autorskog dela
                            </xsl:element>
                        </td>
                    </tr>
                </table>
                <br/>

                <table style="width:30%">
                    <tr>
                        <td style="border-top-width:1px; border-left-width:1px;">Informacije o zahtevu</td>
                    </tr>
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
