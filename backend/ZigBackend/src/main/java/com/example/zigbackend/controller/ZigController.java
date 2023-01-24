package com.example.zigbackend.controller;

import com.example.zigbackend.dto.MetadataSearchParamsDTO;
import com.example.zigbackend.dto.NazivPrijaveDTO;
import com.example.zigbackend.dto.TextSearchDTO;
import com.example.zigbackend.dto.ZahtevZaPriznanjeZigaDTO;
import com.example.zigbackend.model.ZahtevZaPriznanjeZiga;
import com.example.zigbackend.service.ZigService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/zig")
public class ZigController {

    @Autowired
    private ZigService zigService;

    @GetMapping(produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<ZahtevZaPriznanjeZiga> getZahtev(@RequestBody NazivPrijaveDTO brojPrijave) {
        return ResponseEntity.ok(zigService.getZahtev(brojPrijave.getNaziv()));
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public ResponseEntity<ZahtevZaPriznanjeZiga> createZahtevZaPriznanjePatenta(@RequestBody ZahtevZaPriznanjeZigaDTO zahtevZaPriznanjeZigaDTO) throws JAXBException, XMLDBException, IOException {
        ZahtevZaPriznanjeZiga zahtevZaPriznanjeZiga = zigService.createZahtevZaPriznanjeZiga(zahtevZaPriznanjeZigaDTO);

        return ResponseEntity.ok(zahtevZaPriznanjeZiga);
    }

    @GetMapping(path = "/applied", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getAllApplied() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(zigService.getAllApplied());
    }

    @GetMapping(path = "/approved", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getAllApproved() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(zigService.getAllApproved());
    }

    @GetMapping(path = "/canceled", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getAllCanceled() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(zigService.getAllCanceled());
    }

    @GetMapping(path = "/denied", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getAllDenied() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(zigService.getAllDenied());
    }

    @PostMapping(path = "/generate-html", consumes = "application/xml")
    public ResponseEntity<String> generateHTML(@RequestBody NazivPrijaveDTO brojPrijave) {
        String filename = zigService.generateHTML(brojPrijave.getNaziv());

        if (filename != null) {
            return ResponseEntity.ok(filename);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/generate-pdf", consumes = "application/xml")
    public ResponseEntity<String> generatePDF(@RequestBody NazivPrijaveDTO brojPrijave) {
        String filename = zigService.generateHTML(brojPrijave.getNaziv());

        if (filename != null) {
            return ResponseEntity.ok(filename);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/metadata-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getZahteviByMetadata(@RequestBody MetadataSearchParamsDTO metadataSearchParamsDTO) throws IOException {
        if (metadataSearchParamsDTO.getParams().size() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(zigService.getByMetadata(metadataSearchParamsDTO));
    }

    @PutMapping(path = "/text-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjeZiga>> getZahteviByTextSearch(@RequestBody TextSearchDTO textSearchDTO) throws Exception {
        String stripped = textSearchDTO.getTextSearch().strip();

        if ("".equals(stripped)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtevZaPriznanjeZiga> zahtevi = zigService.getByText(stripped, textSearchDTO.isCasesensitive());

        if (zahtevi == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(zahtevi);
    }
}
