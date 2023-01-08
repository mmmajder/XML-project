package zig;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name= "TPravno_lice", propOrder={"poslovnoIme"})
public class PravnoLice extends Lice {
    @XmlElement(name="Poslovno_ime", required=true)
    private String poslovnoIme;

    public String getPoslovnoIme() {
        return poslovnoIme;
    }

    public void setPoslovnoIme(String poslovnoIme) {
        this.poslovnoIme = poslovnoIme;
    }
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\n\t- poslovnoIme: ");
		buffer.append(poslovnoIme);
		return buffer.toString();
	}
}



