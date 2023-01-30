package com.example.zigbackend.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
public class ObradaZahteva {
    private String brojPrijave;
    private SimpleUserDTO sluzbenik;
    private String datumObrade;
    private boolean odbijen;
    private String razlogOdbijanja;
    private String sifra;
}
