package com.example.zigbackend.dto;

import com.example.zigbackend.model.EBoja;
import com.example.zigbackend.model.EOpis_izgleda_ziga;
import com.example.zigbackend.model.ETip_ziga;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ZigDTO {
    private ETip_ziga tipZiga;
    private EOpis_izgleda_ziga opisIzgledaZiga;
    private String drugaVrstaZnakaOpis;
    private String izgledPutanjaDoSlike;
    private String transliteracijaZnaka;
    private String prevodZnaka;
    private String opisZnaka;
//    @XmlElementWrapper(name = "bojaDTO")
//    @XmlElement(name = "boja")
//    private List<EBoja> boja;
    private String bojaConcatenated;
}
