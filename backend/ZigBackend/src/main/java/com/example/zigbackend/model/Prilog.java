package com.example.zigbackend.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Prilog", propOrder={"tipPriloga", "statusPriloga", "putanjaDoPriloga"})
public class Prilog {
    @XmlElement(name="Tip_priloga", required=true)
    private ETip_priloga tipPriloga;
    @XmlElement(name="Status_priloga", required=true)
    private EStatus_priloga statusPriloga;
    @XmlElement(name="Putanja_do_priloga")
    private String putanjaDoPriloga;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\t- tipPriloga: ");
		buffer.append(tipPriloga);
		buffer.append("\n\t- statusPriloga: ");
		buffer.append(statusPriloga);
		buffer.append("\n\t- putanjaDoPriloga: ");
		buffer.append(putanjaDoPriloga);
		return buffer.toString();
	}
}