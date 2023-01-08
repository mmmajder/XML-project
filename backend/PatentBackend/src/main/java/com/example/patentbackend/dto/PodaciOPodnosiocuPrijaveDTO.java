package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PodaciOPodnosiocuPrijaveDTO {
    private Boolean podnosilacPrijaveJeIPronalazac;
    private TLiceDTO podnosilacPrijave;
}
