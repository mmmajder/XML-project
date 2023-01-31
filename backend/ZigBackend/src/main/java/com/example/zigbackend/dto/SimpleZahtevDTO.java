package com.example.zigbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimpleZahtevDTO {
    private String brojPrijave;
    private String datumPodnosenja;
    private SimpleUserDTO podnosioc;
    private boolean obradjen;
}