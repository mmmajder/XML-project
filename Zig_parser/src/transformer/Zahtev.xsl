<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sz="http://www.ftn.uns.ac.rs/zig" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Zahtev za priznanje Ziga</title>
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
                <!--<h1>
                    Broj prijave:
                    <b><xsl:value-of select="//sz:Broj_prijave_ziga"/></b>
                </h1> -->
                
                <center>
                	<h3>Zahtev za priznanje Ziga</h3>
                	<h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>
                </center>
                <!-- <p>Datum podnosenja: <xsl:value-of select="//sz:Datum_podnosenja"/></p> -->
                
                <!-- <p>Podnosilac prijave</p> -->
                <table>
                	<tr><th colspan="7">1. Podnosilac prijave</th></tr>
                	<xsl:for-each select="//sz:Podnosilac_prijave">
						 <tr>
							<xsl:if test="//sz:Podnosilac_prijave//sz:Ime">
							    <th>Ime</th>
							    <th>Prezime</th>
		                	</xsl:if>
		                	<xsl:if test="//sz:Podnosilac_prijave//sz:Poslovno_ime">
							    <th colspan="2">Poslovno ime</th>
	                		</xsl:if>
						 	<th>Ulica</th>
						    <th>Broj</th>
						    <th>Postanski broj</th>
						    <th>Mesto</th>
						    <th>Drzava</th>
					    </tr>
						 <tr>
						 	<xsl:if test="//sz:Podnosilac_prijave//sz:Ime">
						  		<td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Ime"/></td>
		                        <td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Prezime"/></td>
		                	</xsl:if>
		                	<xsl:if test="//sz:Podnosilac_prijave//sz:Poslovno_ime">
					  			<td colspan="2"><xsl:value-of select="//sz:Podnosilac_prijave//sz:Poslovno_ime"/></td>
	                		</xsl:if>
						 	<td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Ulica"/></td>
	                        <td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Broj"/></td>
	                        <td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Postanski_broj"/></td>
	                        <td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Mesto"/></td>
	                        <td><xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Drzava"/></td>
					     </tr>
					     <tr>
						 	<th colspan="2">Telefon</th>
						    <th colspan="3">Email</th>
						    <th colspan="2">Faks</th>
						 </tr>
						 <tr> 
						    <td colspan="2"><xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Telefon"/></td>
						    <td colspan="3"><xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Email"/></td>
						 	<td colspan="2"><xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Faks"/></td>
						 </tr>
				  </xsl:for-each>
				  </table>
				  
				  <br/>
				  
				  <table>
				  	<tr><th colspan="7">2. Punomocnik</th></tr>
					 <tr>
						<xsl:if test="//sz:Punomocnik//sz:Ime">
						    <th>Ime</th>
						    <th>Prezime</th>
	                	</xsl:if>
	                	<xsl:if test="//sz:Punomocnik//sz:Poslovno_ime">
						    <th colspan="2">Poslovno ime</th>
                		</xsl:if>
					 	<th>Ulica</th>
					    <th>Broj</th>
					    <th>Postanski broj</th>
					    <th>Mesto</th>
					    <th>Drzava</th>
				    </tr>
					 <tr>
					 	<xsl:if test="//sz:Punomocnik//sz:Ime">
					  		<td><xsl:value-of select="//sz:Punomocnik//sz:Ime"/></td>
	                        <td><xsl:value-of select="//sz:Punomocnik//sz:Prezime"/></td>
	                	</xsl:if>
	                	<xsl:if test="//sz:Punomocnik//sz:Poslovno_ime">
				  			<td colspan="2"><xsl:value-of select="//sz:Punomocnik//sz:Poslovno_ime"/></td>
                		</xsl:if>
					 	<td><xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Ulica"/></td>
                        <td><xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Broj"/></td>
                        <td><xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Postanski_broj"/></td>
                        <td><xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Mesto"/></td>
                        <td><xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Drzava"/></td>
				     </tr>
				     <tr>
					 	<th colspan="2">Telefon</th>
					    <th colspan="3">Email</th>
					    <th colspan="2">Faks</th>
					 </tr>
					 <tr> 
					    <td colspan="2"><xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Telefon"/></td>
					    <td colspan="3"><xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Email"/></td>
					 	<td colspan="2"><xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Faks"/></td>
					 </tr>
				</table>
				
			  	<br/>
				  
			  	<table>
				  <tr><th colspan="7">3. Predstavnik</th></tr>
					 <tr>
						<xsl:if test="//sz:Predstavnik//sz:Ime">
						    <th>Ime</th>
						    <th>Prezime</th>
	                	</xsl:if>
	                	<xsl:if test="//sz:Predstavnik//sz:Poslovno_ime">
						    <th colspan="2">Poslovno ime</th>
                		</xsl:if>
					 	<th>Ulica</th>
					    <th>Broj</th>
					    <th>Postanski broj</th>
					    <th>Mesto</th>
					    <th>Drzava</th>
				    </tr>
					 <tr>
					 	<xsl:if test="//sz:Predstavnik//sz:Ime">
					  		<td><xsl:value-of select="//sz:Predstavnik//sz:Ime"/></td>
	                        <td><xsl:value-of select="//sz:Predstavnik//sz:Prezime"/></td>
	                	</xsl:if>
	                	<xsl:if test="//sz:Predstavnik//sz:Poslovno_ime">
				  			<td colspan="2"><xsl:value-of select="//sz:Predstavnik//sz:Poslovno_ime"/></td>
                		</xsl:if>
					 	<td><xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Ulica"/></td>
                        <td><xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Broj"/></td>
                        <td><xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Postanski_broj"/></td>
                        <td><xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Mesto"/></td>
                        <td><xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Drzava"/></td>
				     </tr>
				     <tr>
					 	<th colspan="2">Telefon</th>
					    <th colspan="3">Email</th>
					    <th colspan="2">Faks</th>
					 </tr>
					 <tr> 
					    <td colspan="2"><xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Telefon"/></td>
					    <td colspan="3"><xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Email"/></td>
					 	<td colspan="2"><xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Faks"/></td>
					 </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th colspan="2">4. Prijava se odnosi za:</th></tr>
					 <tr>
					 	<th>a)</th>
					 	<xsl:choose>
					 		<xsl:when test="//sz:Zig//sz:Tip_ziga=INDIVIDUALNI_ZIG">
					 			<td>Individualni zig</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Tip_ziga=KOLEKTIVNI_ZIG">
					 			<td>Kolektivni zig</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td>Zig garancije</td>
					 		</xsl:otherwise>
					 	</xsl:choose>
				    </tr>
					 <tr>
					 	<th>b)</th>
					 	<xsl:choose>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga=VERBALNI_ZNAK">
					 			<td>Verbalni znak</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga=GRAFICKI_ZNAK">
					 			<td>Graficki znak</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga=KOMBINOVANI_ZNAK">
					 			<td>Kombinovani znak</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga=TRODIMENZIONALNI_ZNAK">
					 			<td>Trodimenzionalni znak</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Zig//sz:Druga_vrsta_znaka_opis"/></td>
					 		</xsl:otherwise>
					 	</xsl:choose>
				     </tr>
				     <tr>
					 	<th>c) </th>
		 				<td>Izgled znaka: <xsl:value-of select="//sz:Zig//sz:Izgled_putanja_do_slike"/></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</th></tr>
				  <!-- for each pa print svake boje u zaseban red -->
					<tr>
					 	<td></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th>6. Transliteracija znaka</th></tr>
					<tr>
					 	<td><xsl:value-of select="//sz:Zig//sz:Transliteracija_znaka"/></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th>7. Prevod znaka</th></tr>
					<tr>
					 	<td><xsl:value-of select="//sz:Zig//sz:Prevod_znaka"/></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th>8. Opis znaka</th></tr>
					<tr>
					 	<td><xsl:value-of select="//sz:Zig//sz:Opis_znaka"/></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  <tr><th colspan="2">9. Brojevi klasa robe i usluga prema Nicanskoj klasifikaciji</th></tr>
				  <xsl:if test="//sz:Klasa">
				  </xsl:if>
				  <xsl:for-each select="//sz:Klasa">
				  	<tr>
					 	<td><xsl:value-of select="sz:Id_klase"/></td>
					 	<td><xsl:value-of select="sz:Pun_naziv_klase"/></td>
			     	</tr>
				  </xsl:for-each>
				</table>
				
				<br/>
				
				<table>
				  <tr><th>10. Zatrazeno pravo prvenstva i osnov</th></tr>
					<tr>
					 	<td><xsl:value-of select="//sz:Zatrazeno_pravo_prvenstva_i_osnov"/></td>
				     </tr>
				</table>
				
				<br/>
				
				<table>
				  	<tr>
				  		<th>11. Placene takse</th>
				  		<th>Dinara</th>
				  		<th>Potpis podnosioca zahteva</th>
			  		</tr>
			  		<tr>
				  		<td>a) Osnovna taksa</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Osnovna_taksa"/></td>
				  		<td rowspan="4"></td>
			  		</tr>
			  		<tr>
				  		<td>b) Za <xsl:value-of select="count(//sz:Klasa)"/> klase</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Taksa_za_sve_klase"/></td>
			  		</tr>
			  		<tr>
				  		<td>c) Za graficko resenje</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Taksa_za_graficko_resenje"/></td>
			  		</tr>
			  		<tr>
				  		<td>UKUPNO</td>
				  		<td><xsl:value-of select="sum(//sz:Taksa//sz:Osnovna_taksa | //sz:Taksa//sz:Taksa_za_sve_klase | //sz:Taksa//sz:Taksa_za_graficko_resenje)"/></td>
			  		</tr>
				</table>
				
				<br/>
				
				<center>
                	<h4>POPUNJAVA ZAVOD</h4>
                </center>
				
				<table>
				  <tr><th colspan="2">Prilozi uz zahtev:</th></tr>
					<tr>
						<td>Primerak znaka</td>
					 	<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='PRIMERAK_ZNAKA']//..//sz:Status_priloga"/></td> 
				    </tr>
				    <tr>
						<td>Spisak robe i usluga</td>
					 	<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='SPISAK_ROBE_I_USLUGA']//..//sz:Status_priloga"/></td> 
				    </tr>
				    <tr>
						<td>Punomocje</td>
					 	<td><xsl:value-of select="//sz:Prilog_punomocje//sz:Status_priloga"/></td> 
				    </tr>
				    
				    <tr>
						<td>Opsti akt o kolektivnom zigu/zigu garancije</td>
					 	<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='OPSTI_AKT_O_ZIGU']//..//sz:Status_priloga"/></td> 
				    </tr>
				    <tr>
						<td>Dokaz o pravu prvenstva</td>
					 	<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_PRAVU_PRVENSTVA']//..//sz:Status_priloga"/></td> 
				    </tr>
				    <tr>
						<td>Dokaz o uplati takse</td>
					 	<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_UPLATI_TAKSE']//..//sz:Status_priloga"/></td> 
				    </tr>
				</table>
				
				<br/>
				
				<table>
				  	<tr>
				  		<th>Broj prijave ziga:</th>
				  		<th>Datum podnosenja:</th>
			  		</tr>
			  		<tr>
				  		<td><xsl:value-of select="//sz:Broj_prijave_ziga"/></td>
				  		<td><xsl:value-of select="//sz:Datum_podnosenja"/></td>
			  		</tr>
				</table>
				
                <!--
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
                  -->
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
