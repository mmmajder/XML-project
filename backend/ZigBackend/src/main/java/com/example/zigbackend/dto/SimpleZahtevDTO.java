package com.example.zigbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SimpleZahtevDTO {
    private String brojPrijave;
    private String datumPodnosenja;
    private SimpleUserDTO podnosioc;
    private boolean obradjen;
}