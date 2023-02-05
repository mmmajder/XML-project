package com.example.patentbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DostavljanjeDTO {
    private AdresaDTO adresaZaDostavljanje;
    private String nacinDostave;
}
