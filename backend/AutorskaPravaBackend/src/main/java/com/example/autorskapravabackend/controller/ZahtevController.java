package com.example.autorskapravabackend.controller;

import com.example.autorskapravabackend.dto.*;
import com.example.autorskapravabackend.mapper.Mapper;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.service.AutorskaPravaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xmldb.api.base.XMLDBException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/autorskaPrava", produces = MediaType.APPLICATION_XML_VALUE)
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

    @GetMapping(path = "/applied", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaAutorskaPrava>> getAllApplied() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(autorskaPravaService.getAllApplied());
    }

    @GetMapping(path = "/approved", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaAutorskaPrava>> getAllApproved() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(autorskaPravaService.getAllApproved());
    }

    @GetMapping(path = "/canceled", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaAutorskaPrava>> getAllCanceled() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(autorskaPravaService.getAllCanceled());
    }

    @GetMapping(path = "/denied", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaAutorskaPrava>> getAllDenied() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(autorskaPravaService.getAllDenied());
    }

    @PutMapping(path = "/metadata-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<SimpleZahtevDTO>> getZahteviByMetadata(@RequestBody MetadataSearchParamsDTO metadataSearchParamsDTO) throws IOException {
        List<MetadataSearchParams> parsedSearchParams = autorskaPravaService.parseMetadataDTO(metadataSearchParamsDTO);

        System.out.println(metadataSearchParamsDTO);

        if (parsedSearchParams == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtevZaAutorskaPrava> zahtevi = autorskaPravaService.getByMetadata(parsedSearchParams, metadataSearchParamsDTO.isSearchForNeobradjeni());
        List<SimpleZahtevDTO> simpleZahtevDTOs = Mapper.mapToSimpleZahtevs(zahtevi);

        return ResponseEntity.ok(simpleZahtevDTOs);
    }

    @PutMapping(path = "/text-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<SimpleZahtevDTO>> getZahteviByTextSearch(@RequestBody TextSearchDTO textSearchDTO) throws Exception {
        String stripped = textSearchDTO.getTextSearch().trim();

        if (stripped.trim().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtevZaAutorskaPrava> zahtevi = autorskaPravaService.getByText(stripped, textSearchDTO.isCasesensitive(), textSearchDTO.isSearchForNeobradjeni());

        if (zahtevi == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<SimpleZahtevDTO> simpleZahtevDTOs = Mapper.mapToSimpleZahtevs(zahtevi);

        return ResponseEntity.ok(simpleZahtevDTOs);
    }

    @PostMapping("/file-upload/{brojPrijave}/{godinaPrijave}/{tipPriloga}")
    public ResponseEntity<String> uploadPrilog(@PathVariable("brojPrijave") String brojPrijave,
                                               @PathVariable("godinaPrijave") String godinaPrijave,
                                               @PathVariable("tipPriloga") String tipPriloga,
                                               @RequestParam("file") MultipartFile file) {
        try {
            String brojPrijaveZiga = "A-" + godinaPrijave + "/" + brojPrijave;

            if (tipPriloga.trim().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            boolean isOkay = autorskaPravaService.addPrilog(brojPrijaveZiga, tipPriloga, file);

            if (!isOkay) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}