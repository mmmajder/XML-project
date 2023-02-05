package com.example.zigbackend.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
public class DetaljiOZahtevu {
    private SimpleZahtevDTO zahtev;
    private ObradaZahteva obrada;
}
