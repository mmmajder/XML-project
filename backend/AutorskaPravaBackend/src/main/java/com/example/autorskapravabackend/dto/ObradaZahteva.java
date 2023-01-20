package com.example.autorskapravabackend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class ObradaZahteva {
    private SimpleUser sluzbenik;
    private String datumObrade;
    private boolean odbijen;
    private String razlogOdbijanja;
    private String sifra;
}
