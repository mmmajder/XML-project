package com.example.zigbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimpleZahtevDTO {
    private String brojPrijave;
    private String datumPodnosenja;
    private String podnosiocEmail;
    private boolean obradjen;
}