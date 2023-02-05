package patent;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"datumPodnosenjaPrijave", "brojRanijePrijave", "dvoslovnaOznaka"})
public class RanijaPrijava {
	@XmlElement(name="Datum_podnosenja_priave", required = true)
	@XmlSchemaType(name="date")
	private Date datumPodnosenjaPrijave;
	@XmlElement(name="Broj_ranije_prijave", required = true)
	private String brojRanijePrijave;
	@XmlElement(name="Dvoslovna_oznaka", required = true)
	private String dvoslovnaOznaka;
	public Date getDatumaPodnosenjaPrijave() {
		return datumPodnosenjaPrijave;
	}
	public void setDatumaPodnosenjaPrijave(Date datumaPodnosenjaPrijave) {
		this.datumPodnosenjaPrijave = datumaPodnosenjaPrijave;
	}
	public String getBrojRanijePrijave() {
		return brojRanijePrijave;
	}
	public void setBrojRanijePrijave(String brojRanijePrijave) {
		this.brojRanijePrijave = brojRanijePrijave;
	}
	public String getDvoslovnaOznaka() {
		return dvoslovnaOznaka;
	}
	public void setDvoslovnaOznaka(String dvoslovnaOznaka) {
		this.dvoslovnaOznaka = dvoslovnaOznaka;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- Ranija prijava: ");
		buffer.append("\n- datumPodnosenjaPrijave: ");
		buffer.append(datumPodnosenjaPrijave);
		buffer.append("\n- brojRanijePrijave: ");
		buffer.append(brojRanijePrijave);
		buffer.append("\n- dvoslovnaOznaka: ");
		buffer.append(dvoslovnaOznaka);
		return buffer.toString();
	}
	
}
