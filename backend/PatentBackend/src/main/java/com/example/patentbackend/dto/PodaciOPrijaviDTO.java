package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PodaciOPrijaviDTO {

    private String vrstaPrijave;
    private String brojOsnovnePrijave;
    private Date datumPodnosenjaPrijave;

}
