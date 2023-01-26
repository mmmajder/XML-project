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
                
                <center>
                	<h3>Zahtev za priznanje HTML Ziga</h3>
                	<h5>Zavod za intelektualnu svojinu, Kneginje Ljubice broj 5, 11000 Beograd</h5>
                </center>
                
                <table>
                	<tr>
                		<th colspan="3" style="border-top-width:1px; border-left-width:1px;">1. Podnosilac prijave</th>
                	</tr>
						<tr>
						 <td colspan="3" style="border-left-width:1px;">
						 	<xsl:if test="//sz:Podnosilac_prijave//sz:Ime"> 
						  		<xsl:value-of select="//sz:Podnosilac_prijave//sz:Ime"/> <xsl:text>&#x20;</xsl:text>  <!-- &#x9; is tab &#x20; is space-->
						  		<xsl:value-of select="//sz:Podnosilac_prijave//sz:Prezime"/>
		                	</xsl:if>
		                	<xsl:if test="//sz:Podnosilac_prijave//sz:Poslovno_ime">
					  			<i><xsl:value-of select="//sz:Podnosilac_prijave//sz:Poslovno_ime"/></i>
		               		</xsl:if>
		               		<br/> 
						 	<xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Ulica"/> <xsl:text>&#x20;</xsl:text> 
	                     	<xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Broj"/>, 
	                     	<xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Postanski_broj"/>, 
	                     	<xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Mesto"/>, 
	                     	<xsl:value-of select="//sz:Podnosilac_prijave//sz:Adresa//sz:Drzava"/>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left-width:1px;">telefon: <xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Telefon"/></td>
						    <td>e-mail: <xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Email"/></td>
						    <td>faks: <xsl:value-of select="//sz:Podnosilac_prijave//sz:Kontakt//sz:Faks"/></td>
						 </tr>
					 <tr><th colspan="3" style="border-left-width:1px;">2. Punomocnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left-width:1px;">
						 	<xsl:if test="//sz:Punomocnik//sz:Ime"> 
						  		<xsl:value-of select="//sz:Punomocnik//sz:Ime"/> <xsl:text>&#x20;</xsl:text>  <!-- &#x9; is tab &#x20; is space-->
						  		<xsl:value-of select="//sz:Punomocnik//sz:Prezime"/>
		                	</xsl:if>
		                	<xsl:if test="//sz:Punomocnik//sz:Poslovno_ime">
					  			<i><xsl:value-of select="//sz:Punomocnik//sz:Poslovno_ime"/></i>
		               		</xsl:if>
		               		<br/> 
						 	<xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Ulica"/> <xsl:text>&#x20;</xsl:text> 
	                     	<xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Broj"/>, 
	                     	<xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Postanski_broj"/>, 
	                     	<xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Mesto"/>, 
	                     	<xsl:value-of select="//sz:Punomocnik//sz:Adresa//sz:Drzava"/>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left-width:1px;">telefon: <xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Telefon"/></td>
						    <td>e-mail: <xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Email"/></td>
						    <td>faks: <xsl:value-of select="//sz:Punomocnik//sz:Kontakt//sz:Faks"/></td>
						 </tr>
					 <tr><th colspan="3" style="border-left-width:1px;">3. Predstavnik</th></tr>
						<tr>
						 <td colspan="3" style="border-left-width:1px;">
						 	<xsl:if test="//sz:Predstavnik//sz:Ime"> 
						  		<xsl:value-of select="//sz:Predstavnik//sz:Ime"/> <xsl:text>&#x20;</xsl:text>  <!-- &#x9; is tab &#x20; is space-->
						  		<xsl:value-of select="//sz:Predstavnik//sz:Prezime"/>
		                	</xsl:if>
		                	<xsl:if test="//sz:Predstavnik//sz:Poslovno_ime">
					  			<i><xsl:value-of select="//sz:Predstavnik//sz:Poslovno_ime"/></i>
		               		</xsl:if>
		               		<br/> 
						 	<xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Ulica"/> <xsl:text>&#x20;</xsl:text> 
	                     	<xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Broj"/>, 
	                     	<xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Postanski_broj"/>, 
	                     	<xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Mesto"/>, 
	                     	<xsl:value-of select="//sz:Predstavnik//sz:Adresa//sz:Drzava"/>
	                     </td>
					 	</tr>
					     <tr>
						 	<td style="border-left-width:1px;">telefon: <xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Telefon"/></td>
						    <td>e-mail: <xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Email"/></td>
						    <td>faks: <xsl:value-of select="//sz:Predstavnik//sz:Kontakt//sz:Faks"/></td>
						 </tr>
					 </table>
			 <table>
				  <tr>
				  	<th colspan="2" style="border-left-width:1px;">4. Prijava se odnosi za:</th>
				  	<th colspan="2" rowspan="2" style="border-top-width:1px;" >c) izgled znaka:</th>
				  </tr>
				  <tr>
					<th style="border-left-width:1px;">a)</th>
				 	<xsl:choose>
				 		<xsl:when test="//sz:Zig//sz:Tip_ziga='INDIVIDUALNI_ZIG'">
				 			<td>Individualni zig</td>
				 		</xsl:when>
				 		<xsl:when test="//sz:Zig//sz:Tip_ziga='KOLEKTIVNI_ZIG'">
				 			<td>Kolektivni zig</td>
				 		</xsl:when>
				 		<xsl:otherwise>
				 			<td>Zig garancije</td>
				 		</xsl:otherwise>
				 	</xsl:choose>
    			  </tr>
				  <tr>
				 	<th style="border-left-width:1px;"> b)</th>
				 	<xsl:choose>
				 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='VERBALNI_ZNAK'">
				 			<td>Verbalni znak</td>
				 		</xsl:when>
				 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='GRAFICKI_ZNAK'">
				 			<td>Graficki znak</td>
				 		</xsl:when>
				 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='KOMBINOVANI_ZNAK'">
				 			<td>Kombinovani znak</td>
				 		</xsl:when>
				 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='TRODIMENZIONALNI_ZNAK'">
				 			<td>Trodimenzionalni znak</td>
				 		</xsl:when>
				 		<xsl:otherwise>
				 			<td>Druga vrsta: <xsl:value-of select="//sz:Zig//sz:Druga_vrsta_znaka_opis"/></td>
				 		</xsl:otherwise>
				 	</xsl:choose>
				 	<td colspan="2" rowspan="5">
				 		<xsl:element name="img">
			 				<xsl:attribute name="width">100%</xsl:attribute>
			 				<xsl:attribute name="maxWidth">250px</xsl:attribute>
			 				<xsl:attribute name="maxHeight">250px</xsl:attribute>
			              	<xsl:attribute name="src">
			                C:\Faks\VII\XML i veb servisi\XML-project\Zig_parser\data\<xsl:value-of select="//sz:Zig//sz:Izgled_putanja_do_slike"/>
			              	</xsl:attribute>
			            </xsl:element>
				 	</td>
			      </tr>
			      
			      <xsl:if test="//sz:Boja">
			      	<tr><td colspan="2" style="border-left-width:1px;">
			      		<b>5. Naznacenje boje, odnosno boja iz kojih se znak sastoji:</b> <br/>
				  		<xsl:for-each select="//sz:Boja">
		 					<xsl:value-of select="."/>, <xsl:text>&#x20;</xsl:text>
				  		</xsl:for-each>
		      		</td></tr>
			      </xsl:if>
			      
			      <xsl:if test="//sz:Zig//sz:Transliteracija_znaka">
			      	<tr><td colspan="2" style="border-left-width:1px;">
			      		<b>6. Transliteracija znaka:</b> <br/>
	 					<xsl:value-of select="//sz:Zig//sz:Transliteracija_znaka"/>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="//sz:Zig//sz:Prevod_znaka">
			      	<tr><td colspan="2" style="border-left-width:1px;">
			      		<b>7. Prevod znaka</b> <br/>
	 					<xsl:value-of select="//sz:Zig//sz:Prevod_znaka"/>
			  		</td></tr>
			      </xsl:if>
			      
				  <xsl:if test="//sz:Zig//sz:Opis_znaka">
			      	<tr><td colspan="2" style="border-left-width:1px;">
			      		<b>8. Opis znaka</b> <br/>
	 					<xsl:value-of select="//sz:Zig//sz:Opis_znaka"/>
			  		</td></tr>
			      </xsl:if>
			      
			      </table>
			 <table>
			      
				  <tr><th colspan="3" style="border-left-width:1px;">9. Brojevi klasa robe i usluga prema Nicanskoj klasifikaciji</th></tr>
				  <xsl:if test="//sz:Klasa">
					  <xsl:for-each select="//sz:Klasa">
						<tr>
							<td style="border-left-width:1px;"><xsl:value-of select="sz:Id_klase"/></td>
							<td colspan="2"><xsl:value-of select="sz:Pun_naziv_klase"/></td>
						</tr>
					  </xsl:for-each>
				  </xsl:if>
				  
				  <xsl:if test="//sz:Zatrazeno_pravo_prvenstva_i_osnov">
			      	<tr><td colspan="3" style="border-left-width:1px;">
			      		<b>10. Zatrazeno pravo prvenstva i osnov</b> <br/>
	 					<xsl:value-of select="//sz:Zatrazeno_pravo_prvenstva_i_osnov"/>
			  		</td></tr>
			      </xsl:if>
			      
			      <tr>
				  		<th style="border-left-width:1px;">11. Placene takse</th>
				  		<th>Dinara</th>
				  		<th rowspan="5" style="vertical-align: top; text-align: center;">Potpis podnosioca zahteva</th>
			  		</tr>
			  		<tr>
				  		<td style="border-left-width:1px;">a) Osnovna taksa</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Osnovna_taksa"/></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left-width:1px;">b) Za <xsl:value-of select="count(//sz:Klasa)"/> klase</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Taksa_za_sve_klase"/></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left-width:1px;">c) Za graficko resenje</td>
				  		<td><xsl:value-of select="//sz:Taksa//sz:Taksa_za_graficko_resenje"/></td>
			  		</tr>
			  		<tr>
				  		<td style="border-left-width:1px;">UKUPNO</td>
				  		<td><xsl:value-of select="sum(//sz:Taksa//sz:Osnovna_taksa | //sz:Taksa//sz:Taksa_za_sve_klase | //sz:Taksa//sz:Taksa_za_graficko_resenje)"/></td>
			  		</tr>
				</table>
				
				<br/>
				
				<center>
                	<h4>POPUNJAVA ZAVOD</h4>
                </center>
				
				<table>			
				  <tr>
				  	<th colspan="2" style="border-top-width:1px; border-left-width:1px;">Prilozi uz zahtev:</th>
				  	<td rowspan="7" style="text-align: center; border-top-width:1px;">Broj prijave ziga: <br/>
				  		<xsl:value-of select="//sz:Broj_prijave_ziga"/> <br/> <br/>
				  		Datum podnosenja: <br/>
				  		<xsl:value-of select="format-dateTime(//sz:Datum_podnosenja, '[M01].[D01].[Y0001].')"/></td>
				  </tr>
					<tr>
						<td style="border-left-width:1px;">Primerak znaka</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='PRIMERAK_ZNAKA']//..//sz:Status_priloga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='PRIMERAK_ZNAKA']//..//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose> 
				    </tr>
				    <tr>
						<td style="border-left-width:1px;">Spisak robe i usluga</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='SPISAK_ROBE_I_USLUGA']//..//sz:Status_priloga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='SPISAK_ROBE_I_USLUGA']//..//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose> 
				    </tr>
				    <tr>
						<td style="border-left-width:1px;">Punomocje</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog_punomocje//sz:Status_priloga='PREDATO_RANIJE'">
					 			<td>PREDATO RANIJE</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='BICE_NAKNADNO_PREDATO'">
					 			<td>BICE NAKNADNO PREDATO</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Zig//sz:Opis_izgleda_ziga='NIJE_POTREBNO'">
					 			<td>NIJE POTREBNO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog_punomocje//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose>
				    </tr>
				    
				    <tr>
						<td style="border-left-width:1px;">Opsti akt o kolektivnom zigu/zigu garancije</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='OPSTI_AKT_O_ZIGU']//..//sz:Status_priloga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='OPSTI_AKT_O_ZIGU']//..//sz:Status_priloga='NIJE_POTREBNO'">
					 			<td>NIJE POTREBNO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='OPSTI_AKT_O_ZIGU']//..//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose> 
				    </tr>
				    <tr>
						<td style="border-left-width:1px;">Dokaz o pravu prvenstva</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_PRAVU_PRVENSTVA']//..//sz:Status_priloga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_PRAVU_PRVENSTVA']//..//sz:Status_priloga='NIJE_POTREBNO'">
					 			<td>NIJE POTREBNO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_PRAVU_PRVENSTVA']//..//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose> 
				    </tr>
				    <tr>
						<td style="border-left-width:1px;">Dokaz o uplati takse</td>
						<xsl:choose>
					 		<xsl:when test="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_UPLATI_TAKSE']//..//sz:Status_priloga='NIJE_PREDATO'">
					 			<td>NIJE PREDATO</td>
					 		</xsl:when>
					 		<xsl:otherwise>
					 			<td><xsl:value-of select="//sz:Prilog//sz:Tip_priloga[text()='DOKAZ_O_UPLATI_TAKSE']//..//sz:Status_priloga"/></td> 
					 		</xsl:otherwise>
					 	</xsl:choose> 
				    </tr>
				</table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>