package com.example.zigbackend.controller;


import com.example.zigbackend.dto.BrojPrijaveDTO;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.service.ZigService;
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
@RequestMapping(value = "/download")
public class DownloadController {

    private ZigService zigService;

    // for testing vvv
    @GetMapping(path = "/html/{brojPrijaveZigaId}-{brojPrijaveZigaGodina}", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> generateHTMLPathVariable(@PathVariable("brojPrijaveZigaId") String brojPrijaveZigaId,
                                                                        @PathVariable("brojPrijaveZigaGodina") String brojPrijaveZigaGodina) throws IOException {
        String brojPrijave = brojPrijaveZigaId.concat("/").concat(brojPrijaveZigaGodina);
        String filename = zigService.generateHTML(brojPrijave);
        String[] filenameParts = filename.split("\\\\");
        filename = filenameParts[filenameParts.length-1];
        ByteArrayInputStream byteFile = zigService.getGenerated(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + filename);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XHTML_XML).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/pdf/{brojPrijaveZigaId}-{brojPrijaveZigaGodina}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePDFPathVariable(@PathVariable("brojPrijaveZigaId") String brojPrijaveZigaId,
                                                                       @PathVariable("brojPrijaveZigaGodina") String brojPrijaveZigaGodina) throws IOException {
        String brojPrijave = brojPrijaveZigaId.concat("/").concat(brojPrijaveZigaGodina);
        String filename = zigService.generatePDF(brojPrijave);
        ByteArrayInputStream byteFile = zigService.getGenerated(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + filename);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/rdf/{brojPrijaveZigaId}-{brojPrijaveZigaGodina}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateRDFPathVariable(@PathVariable("brojPrijaveZigaId") String brojPrijaveZigaId,
                                                                       @PathVariable("brojPrijaveZigaGodina") String brojPrijaveZigaGodina) {
        String brojPrijave = brojPrijaveZigaId.concat("/").concat(brojPrijaveZigaGodina);
        ByteArrayInputStream byteFile = zigService.generateRDF(brojPrijave);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata-" + brojPrijave + ".rdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }
    // for testing ^^^




    @PostMapping(path = "/html", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
    public ResponseEntity<InputStreamResource> generateHTML(@RequestBody BrojPrijaveDTO brojPrijaveDto) throws IOException {
        String brojPrijave = brojPrijaveDto.getBroj();
        String filename = zigService.generateHTML(brojPrijave);
        String[] filenameParts = filename.split("\\\\");
        filename = filenameParts[filenameParts.length-1];
        ByteArrayInputStream byteFile = zigService.getGenerated(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + filename);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_XHTML_XML).body(new InputStreamResource(byteFile));
    }

    @PostMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody BrojPrijaveDTO brojPrijaveDto) throws IOException {
        String brojPrijave = brojPrijaveDto.getBroj();
        String filename = zigService.generatePDF(brojPrijave);
        ByteArrayInputStream byteFile = zigService.getGenerated(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=review-" + filename);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @PostMapping(path = "/rdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateRDF(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = zigService.generateRDF(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata-" + brojPrijave.getBroj() + ".rdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @PostMapping(path = "/json", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateJSON(@RequestBody BrojPrijaveDTO brojPrijave) {
        ByteArrayInputStream byteFile = zigService.generateJSON(brojPrijave.getBroj());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=metadata-" + brojPrijave.getBroj() + ".json");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }

    @GetMapping(path = "/prilog/{fileName}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPrilogFile(@PathVariable("fileName") String fileName) throws IOException {
        ByteArrayInputStream byteFile = zigService.getPrilog(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + fileName);

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(byteFile));
    }
}
