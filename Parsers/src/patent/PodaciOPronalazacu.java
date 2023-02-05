package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"pronalazacNeZeliDaBudeNaveden", "pronalazac"})
public class PodaciOPronalazacu {
	
	@XmlElement(name="Pronalazac_ne_zeli_da_bude_naveden", required = true)
	private boolean pronalazacNeZeliDaBudeNaveden;
	
	@XmlElement(name="Pronalazac")
	private TFizickoLice pronalazac;

	public boolean isPronalazacNeZeliDaBudeNaveden() {
		return pronalazacNeZeliDaBudeNaveden;
	}

	public void setPronalazacNeZeliDaBudeNaveden(boolean pronalazacNeZeliDaBudeNaveden) {
		this.pronalazacNeZeliDaBudeNaveden = pronalazacNeZeliDaBudeNaveden;
	}

	public TFizickoLice getPronalazac() {
		return pronalazac;
	}

	public void setPronalazac(TFizickoLice pronalazac) {
		this.pronalazac = pronalazac;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- pronalazacNeZeliDaBudeNaveden: ");
		buffer.append(pronalazacNeZeliDaBudeNaveden);
		buffer.append("\n- pronalazac: ");
		buffer.append(pronalazac);
		return buffer.toString();
	}
	
	

}
