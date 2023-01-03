package com.example.patentbackend.controller;

import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.service.PatentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/patent", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatentController {

    @Autowired
    private PatentService patentService;

    @GetMapping("/{brojPrijave}")
    public ResponseEntity<ZahtevZaPriznanjePatenta> getZahtevZaPriznanjePatentaBy(@PathVariable String brojPrijave) {
        return ResponseEntity.ok(patentService.getZahtevZaPriznanjePatenta(brojPrijave));
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<ZahtevZaPriznanjePatenta> createZahtevZaPriznanjePatenta(@RequestBody ZahtevZaPriznanjePatentaDTO zahtevZaPriznanjePatentaDTO) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = patentService.createZahtevZaPriznanjePatenta(zahtevZaPriznanjePatentaDTO);
        return ResponseEntity.ok(zahtevZaPriznanjePatenta);
    }
}
