package com.example.patentbackend.controller;

import com.example.patentbackend.dto.NazivPrijaveDTO;
import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.service.PatentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/patent", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatentController {

    @Autowired
    private PatentService patentService;

    @GetMapping(produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<ZahtevZaPriznanjePatenta> getZahtevZaPriznanjePatentaBy(@RequestBody NazivPrijaveDTO brojPrijave) {
        return ResponseEntity.ok(patentService.getZahtevZaPriznanjePatenta(brojPrijave.getNaziv()));
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<ZahtevZaPriznanjePatenta> createZahtevZaPriznanjePatenta(@RequestBody ZahtevZaPriznanjePatentaDTO zahtevZaPriznanjePatentaDTO) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = patentService.createZahtevZaPriznanjePatenta(zahtevZaPriznanjePatentaDTO);
        return ResponseEntity.ok(zahtevZaPriznanjePatenta);
    }

    @GetMapping(path = "/pending", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllPending() {
        return ResponseEntity.ok(patentService.getAllPending());
    }

    @GetMapping(path = "/accepted", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllAccepted() {
        return ResponseEntity.ok(patentService.getAllAccepted());
    }

//    @PutMapping(path = "/accept", consumes = "application/xml", produces = "application/xml")

    @PostMapping(path = "/generate-html", consumes = "application/xml")
    public ResponseEntity<String> generateHTML(@RequestBody NazivPrijaveDTO brojPrijave) {
        if (patentService.generateHTML(brojPrijave)) {
            return ResponseEntity.ok("Uspesno generisan html");
        }
        return ResponseEntity.ok("Doslo je do greske prilikom generisanja html");
    }

    @PostMapping(path = "/generate-pdf", consumes = "application/xml")
    public ResponseEntity<String> generatePDF(@RequestBody NazivPrijaveDTO brojPrijave) {
        if (patentService.generatePDF(brojPrijave)) {
            return ResponseEntity.ok("Uspesno generisan pdf");
        }
        return ResponseEntity.ok("Doslo je do greske prilikom generisanja pdf");
    }


}
