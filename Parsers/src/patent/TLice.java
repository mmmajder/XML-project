package patent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="TLice", propOrder={"osnovniPodaciOLicu"})
public class TLice {
	@XmlElement(name="Osnovni_podaci_o_licu", required=true)
	private OsnovniPodaciOLicu osnovniPodaciOLicu;

	public OsnovniPodaciOLicu getOsnovniPodaciOLicu() {
		return osnovniPodaciOLicu;
	}

	public void setOsnovniPodaciOLicu(OsnovniPodaciOLicu osnovniPodaciOLicu) {
		this.osnovniPodaciOLicu = osnovniPodaciOLicu;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n- osnovniPodaciOLicu: ");
		buffer.append(osnovniPodaciOLicu);
		return buffer.toString();
	}
	
}
