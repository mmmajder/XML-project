package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"podnosilacPrijaveJeIPronalazac", "podnosilacPrijave"})
public class PodaciOPodnosiocuPrijave {
	@XmlElement(name="Podnosilac_prijave_je_i_pronalazac", required=true)
	private Boolean podnosilacPrijaveJeIPronalazac;
	
	@XmlElement(name="Podnosilac_prijave", required=true)
	private TLice podnosilacPrijave;
	
	public Boolean getPodnosilacPrijaveJeIPronalazac() {
		return podnosilacPrijaveJeIPronalazac;
	}

	public void setPodnosilacPrijaveJeIPronalazac(Boolean podnosilacPrijaveJeIPronalazac) {
		this.podnosilacPrijaveJeIPronalazac = podnosilacPrijaveJeIPronalazac;
	}

	public TLice getPodnosilacPrijave() {
		return podnosilacPrijave;
	}

	public void setPodnosilacPrijave(TLice podnosilacPrijave) {
		this.podnosilacPrijave = podnosilacPrijave;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- podnosilacPrijaveJeIPronalazac: ");
		buffer.append(podnosilacPrijaveJeIPronalazac);
		buffer.append("\n- podnosilacPrijave: ");
		buffer.append(podnosilacPrijave);
		return buffer.toString();
	}
}
