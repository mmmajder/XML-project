package com.example.autorskapravabackend.model;

import lombok.*;

import javax.xml.bind.annotation.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TLice")
@XmlType(name = "TLice", propOrder = {"brojTelefona", "email"})
public class TLice {

    @XmlElement(name = "Broj_telefona", required = true)
    private String brojTelefona;
    @XmlElement(name = "Email", required = true)
    private String email;

    @Override
    public String toString() {
        return "TLice {" +
                "\n\tbrojTelefona='" + brojTelefona + '\'' +
                ",\n\temail='" + email + '\'' +
                "\n}";
    }
}
