package com.example.autorskapravabackend.dto;

import com.example.autorskapravabackend.model.Autor;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TAutorDTO {
//    private boolean anoniman;
    private Autor podaciOAutoru;
}
