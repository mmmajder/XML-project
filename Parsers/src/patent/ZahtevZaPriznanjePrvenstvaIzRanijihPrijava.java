package patent;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"ranijePrijave"})
public class ZahtevZaPriznanjePrvenstvaIzRanijihPrijava {
	@XmlElement(name="Ranija_prijava", required=false)
	private List<TRanijihPrijavaZaPriznanjePrvenstva> ranijePrijave = new ArrayList<TRanijihPrijavaZaPriznanjePrvenstva>();
	
	public List<TRanijihPrijavaZaPriznanjePrvenstva> getRanijePrijave() {
		return ranijePrijave;
	}
	public void setRanijePrijave(List<TRanijihPrijavaZaPriznanjePrvenstva> ranijePrijave) {
		this.ranijePrijave = ranijePrijave;
	}
	@Override
	public String toString() {
		return "ZahtevZaPriznanjePrvenstvaIzRanijihPrijava [ranijePrijave=" + ranijePrijave + "]";
	}
	
	
}
