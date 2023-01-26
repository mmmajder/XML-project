package zig;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name= "PrilogPunomocje", propOrder={"statusPriloga", "putanjaDoPriloga"})
public class PrilogPunomocje {
    @XmlElement(name="Status_priloga")
    private EStatus_prilog_punomocje statusPriloga;
    @XmlElement(name="Putanja_do_priloga")
    private String putanjaDoPriloga;

    public EStatus_prilog_punomocje getStatusPriloga() {
        return statusPriloga;
    }

    public void setStatusPriloga(EStatus_prilog_punomocje statusPriloga) {
        this.statusPriloga = statusPriloga;
    }

    public String getPutanjaDoPriloga() {
        return putanjaDoPriloga;
    }

    public void setPutanjaDoPriloga(String putanjaDoPriloga) {
        this.putanjaDoPriloga = putanjaDoPriloga;
    }

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- statusPriloga: ");
		buffer.append(statusPriloga);
		buffer.append("\n\t- putanjaDoPriloga: ");
		buffer.append(putanjaDoPriloga);
		return buffer.toString();
	}
}