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
                    margin: 50px auto 50px auto;
                    width: 90%;
                    }
                    th, td {
                    text-align: left;
                    padding: 30px;
                    }
                    tr:nth-child(even){ background-color: #f2f2f2 }
                    th {
                    background-color: #4caf50;
                    font-family: sans-serif;
                    color: white;
                    }
                    tr { border: 1px solid darkgrey; }
                    tr:hover {
                    font-style: italic;
                    background-color: #cae8cb;
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
                <h1>
                    Patent:
                    <b><xsl:value-of select="//pat:Broj_prijave"/></b>
                </h1>
                <p>Datum prijema: <xsl:value-of select="//pat:Osnovne_informacije_o_zahtevu_za_priznanje_patenta/Datum_prijema"/></p>
                <p>Priznati datum prijema: <xsl:value-of select="//pat:Osnovne_informacije_o_zahtevu_za_priznanje_patenta/Priznati_datum_podnosenja"/></p>
                <p>Pecat i potpis:<br/>
                Republika Srbija<br/>
                Zavod za intelektualnu svojinu<br/>
                Kneginje Ljubice broj 5<br/>
                11000 Beograd</p>
                <h1>Zahtev za priznanje patenta</h1>
                <p>Naziv pronalaska:<br/>
                    Naziv na srpskom:  <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='SRPSKI']/@Naziv"/><br/>
                    Naziv na engleskom:  <xsl:value-of select="//pat:Naziv_pronalaska[@Jezik='ENGLESKI']/@Naziv"/>
                </p>
                <p>Podnosilac prijave<br/>
                    Ime: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Ime"/><br/>
                    Prezime: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Prezime"/><br/>
                    Poslovno ime: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Poslovno_ime"/><br/>
                    <p>Adresa <br/>
                        Ulica: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Ulica"/><br/>
                        Broj: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_u_ulici"/><br/>
                        Postanski broj: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Postanski_broj"/><br/>
                        Mesto: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Mesto"/><br/>
                        Drzava: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Drzava"/><br/>
                    </p>
                    Broj telefona: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_telefona"/><br/>
                    Broj faksa: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Broj_faksa"/><br/>
                    Email: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:E_posta"/><br/>
                    Drzavljanstvo: <xsl:value-of select="//pat:Podaci_o_podnosiocu_prijave//pat:Drzavljanstvo"/><br/>
                </p>

                <p>Pronalazac<br/>
                    Ime: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Ime"/><br/>
                    Prezime: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Prezime"/><br/>
                    <p>Adresa <br/>
                        Ulica: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Ulica"/><br/>
                        Broj: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Broj_u_ulici"/><br/>
                        Postanski broj: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Postanski_broj"/><br/>
                        Mesto: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Mesto"/><br/>
                        Drzava: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Drzava"/><br/>
                    </p>
                    Broj telefona: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Broj_telefona"/><br/>
                    Broj faksa: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:Broj_faksa"/><br/>
                    Email: <xsl:value-of select="//pat:Podaci_o_pronalazacu//pat:E_posta"/><br/>
                </p>

                <p>Punomocnik<br/>
                    Vrsta punomocnika: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Vrsta_punomocnika"/><br/>
                    Zajednicki predstavnik: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Zajednicki_predstavnik"/><br/>
                    Ime: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Ime"/><br/>
                    Prezime: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Prezime"/><br/>
                    Poslovno ime: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Poslovno_ime"/><br/>
                    <p>Adresa <br/>
                        Ulica: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Ulica"/><br/>
                        Broj: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Broj_u_ulici"/><br/>
                        Postanski broj: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Postanski_broj"/><br/>
                        Mesto: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Mesto"/><br/>
                        Drzava: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Drzava"/><br/>
                    </p>
                    Broj telefona: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Broj_telefona"/><br/>
                    Broj faksa: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:Broj_faksa"/><br/>
                    Email: <xsl:value-of select="//pat:Podaci_o_punomocniku//pat:E_posta"/><br/>
                </p>
                <p>Dostavljanje<br/>
                    <p>Adresa <br/>
                        Ulica: <xsl:value-of select="//pat:Dostavljanje//pat:Ulica"/><br/>
                        Broj: <xsl:value-of select="//pat:Dostavljanje//pat:Broj_u_ulici"/><br/>
                        Postanski broj: <xsl:value-of select="//pat:Dostavljanje//pat:Postanski_broj"/><br/>
                        Mesto: <xsl:value-of select="//pat:Dostavljanje//pat:Mesto"/><br/>
                        Drzava: <xsl:value-of select="//pat:Dostavljanje//pat:Drzava"/><br/>
                    </p>
                    Nacin dostave: <xsl:value-of select="//pat:Dostavljanje//pat:Nacin_dostave"/><br/>
                </p>
                <p>Podaci o prijavi<br/>
                    Vrsta prijave: <xsl:value-of select="//pat:Podaci_o_prijavi//pat:Vrsta_prijave"/><br/>
                    Broj osnovne prijave: <xsl:value-of select="//pat:Podaci_o_prijavi//pat:Broj_osnovne_prijave"/><br/>
                    Datum podnosnjea prijave: <xsl:value-of select="//pat:Podaci_o_prijavi//pat:Datum_podnosenja_prijave"/><br/>
                </p>
                <p>Zahtev za priznanje prvenstva iz ranijih prijava<br/>
                    <xsl:for-each select="//pat:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava">
                        Broj ranije prijave: <xsl:value-of select="//pat:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava//pat:Broj_ranije_prijave"/><br/>
                        Datum podnosenja prijave: <xsl:value-of select="//pat:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava//pat:Datum_podnosenja_priave"/><br/>
                        Dvoslovna oznaka: <xsl:value-of select="//pat:Zahtev_za_priznanje_prvenstva_iz_ranijih_prijava//pat:Dvoslovna_oznaka"/><br/>
                    </xsl:for-each>
                </p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
