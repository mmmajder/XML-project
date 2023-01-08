package com.example.autorskapravabackend.controller;

import com.example.autorskapravabackend.dto.ZahtevZaAutorskaPravaDTO;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.service.AutorskaPravaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/autorskaPrava", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZahtevController {

    private AutorskaPravaService autorskaPravaService;

    @GetMapping(produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<ZahtevZaAutorskaPrava> getZahtevByBrojPrijave(@RequestBody String brojPrijave) {
        return ResponseEntity.ok(autorskaPravaService.getZahtev(brojPrijave));
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<ZahtevZaAutorskaPrava> createZahtev(@RequestBody ZahtevZaAutorskaPravaDTO dto) {
        return ResponseEntity.ok(autorskaPravaService.createZahtevZaAutorskaPrava(dto));
    }
}