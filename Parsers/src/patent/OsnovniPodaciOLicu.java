package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Osnovni_podaci_o_licu")
@XmlType(name="", propOrder={"adresaLica", "brojTelefona", "brojFaksa", "ePosta"})
public class OsnovniPodaciOLicu {
	
	@XmlElement(name="Adresa_lica", required=true)
	private Adresa adresaLica;
	@XmlElement(name="Broj_telefona", required=true)
	private String brojTelefona;
	@XmlElement(name="Broj_faksa", required=true)
	private String brojFaksa;
	@XmlElement(name="E_posta", required=true)
	private String ePosta;
	
	public Adresa getAdresaLica() {
		return adresaLica;
	}
	public void setAdresaLica(Adresa adresaLica) {
		this.adresaLica = adresaLica;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getBrojFaksa() {
		return brojFaksa;
	}
	public void setBrojFaksa(String brojFaksa) {
		this.brojFaksa = brojFaksa;
	}
	public String getePosta() {
		return ePosta;
	}
	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- adresaLica: ");
		buffer.append(adresaLica);
		buffer.append("\n- brojTelefona: ");
		buffer.append(brojTelefona);
		buffer.append("\n- brojFaksa: ");
		buffer.append(brojFaksa);
		buffer.append("\n- ePosta: ");
		buffer.append(ePosta);
		return buffer.toString();
	}
	
	
}
