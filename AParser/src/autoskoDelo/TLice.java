package autoskoDelo;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TLice")
@XmlType(name = "TLice", propOrder = {"brojTelefona", "email"})
public class TLice {

    @XmlElement(name = "Broj_telefona", required = true)
    private String brojTelefona;

    @XmlElement(name = "Email", required = true)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    @Override
    public String toString() {
        return "TLice {" +
                "\n\tbrojTelefona='" + brojTelefona + '\'' +
                ",\n\temail='" + email + '\'' +
                "\n}";
    }
}
