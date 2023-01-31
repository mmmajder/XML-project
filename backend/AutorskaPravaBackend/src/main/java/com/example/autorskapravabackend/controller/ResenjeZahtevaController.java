package com.example.autorskapravabackend.controller;

import com.example.autorskapravabackend.dto.BrojPrijaveDTO;
import com.example.autorskapravabackend.dto.DetaljiOZahtevu;
import com.example.autorskapravabackend.dto.ObradaZahteva;
import com.example.autorskapravabackend.service.ResenjeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/autorskaPravaResenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeZahtevaController {

    private ResenjeService resenjeService;

    @PostMapping(path = "/resenjeZahteva", consumes = "application/xml", produces = "application/xml")
    public DetaljiOZahtevu getResenjeZahteva(@RequestBody BrojPrijaveDTO brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return resenjeService.getResenjeZahteva(brojPrijave.getBroj());
    }

    @PostMapping(value = "obradiZahtev", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> obradiZahtev(@RequestBody ObradaZahteva obradaZahteva) {
        try {
            resenjeService.obradiZahtev(obradaZahteva);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
