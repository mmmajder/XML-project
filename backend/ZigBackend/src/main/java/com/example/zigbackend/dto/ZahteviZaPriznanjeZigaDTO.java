package com.example.zigbackend.dto;

import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ZahteviZaPriznanjeZigaDTO {
    private List<ZahtevZaPriznanjeZiga> zahtevi;
}
