package com.example.autorskapravabackend.controller;


import com.example.autorskapravabackend.dto.BrojPrijaveDTO;
import com.example.autorskapravabackend.service.AutorskaPravaService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/download")
public class DownloadController {

    private AutorskaPravaService autorskaPravaService;

    @PostMapping(path = "/html", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> generateHTML(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = autorskaPravaService.generateHTML(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + brojPrijave.getBroj() + ".html");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XHTML_XML).body(new InputStreamResource(byteFile));
    }

    @PostMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = autorskaPravaService.generatePDF(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + brojPrijave.getBroj() + ".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

//    @PostMapping(path = "/rdf", consumes = "application/xml")
//    public ResponseEntity<String> generateRDF(@RequestBody BrojPrijaveDTO brojPrijave) {
//        if (autorskaPravaService.generateRDF(brojPrijave.getBroj())) {
//            return ResponseEntity.ok("Uspesno generisan rdf");
//        }
//        return ResponseEntity.ok("Doslo je do greske prilikom generisanja rdf");
//    }
//
//    @PostMapping(path = "/json", consumes = "application/xml")
//    public ResponseEntity<String> generateJSON(@RequestBody BrojPrijaveDTO brojPrijave) {
//        if (autorskaPravaService.generateJSON(brojPrijave.getBroj())) {
//            return ResponseEntity.ok("Uspesno generisan json-a");
//        }
//        return ResponseEntity.ok("Doslo je do greske prilikom generisanja json-a");
//    }
}
