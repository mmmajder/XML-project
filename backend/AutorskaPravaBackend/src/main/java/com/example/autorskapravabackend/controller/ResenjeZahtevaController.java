package com.example.autorskapravabackend.controller;

import com.example.autorskapravabackend.dto.BrojPrijaveDTO;
import com.example.autorskapravabackend.dto.DetaljiOZahtevu;
import com.example.autorskapravabackend.service.ResenjeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/autorskaPravaResenje", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResenjeZahtevaController {

    private ResenjeService resenjeService;

    @PostMapping(path = "/resenjeZahteva", consumes = "application/xml", produces = "application/xml")
    public DetaljiOZahtevu getResenjeZahteva(@RequestBody BrojPrijaveDTO brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return resenjeService.getResenjeZahteva(brojPrijave.getBroj());
    }

//    @PostMapping(path = "/obrada", consumes = "application/xml", produces = "application/xml")
//    public DetaljiOZahtevu obradiResenje(@RequestBody BrojPrijaveDTO brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        return resenjeService.getResenjeZahteva(brojPrijave.getBroj());
//    }
}
