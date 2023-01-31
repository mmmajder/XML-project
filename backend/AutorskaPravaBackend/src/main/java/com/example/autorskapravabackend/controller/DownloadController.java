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
import java.io.IOException;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/autorskaPrava/download")
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

    @PostMapping(path = "/rdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateRDF(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = autorskaPravaService.generateRDF(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata-" + brojPrijave.getBroj() + ".rdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @PostMapping(path = "/json", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateJSON(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = autorskaPravaService.generateJSON(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata-" + brojPrijave.getBroj() + ".json");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/prilog/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPrilogFile(@PathVariable("fileName") String fileName) throws IOException {
        ByteArrayInputStream byteFile = autorskaPravaService.getPrilog(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + fileName);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }
}
