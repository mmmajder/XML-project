package com.example.patentbackend.controller;

import com.example.patentbackend.dto.*;
import com.example.patentbackend.mapper.Mapper;
import com.example.patentbackend.model.ZahtevZaPriznanjePatenta;
import com.example.patentbackend.service.PatentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllPending() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(patentService.getAllPending());
    }

    @GetMapping(path = "/accepted", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllAccepted() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(patentService.getAllAccepted());
    }

    @GetMapping(path = "/canceled", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllCanceled() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(patentService.getAllCanceled());
    }

    @GetMapping(path = "/denied", produces = "application/xml")
    public ResponseEntity<List<ZahtevZaPriznanjePatenta>> getAllDenied() throws JAXBException, XMLDBException {
        return ResponseEntity.ok(patentService.getAllDenied());
    }

    @PutMapping(path = "/metadata-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<SimpleZahtevDTO>> getZahteviByMetadata(@RequestBody MetadataSearchParamsDTO metadataSearchParamsDTO) throws IOException {
        List<MetadataSearchParams> parsedSearchParams = patentService.parseMetadataDTO(metadataSearchParamsDTO);

        System.out.println(metadataSearchParamsDTO);

        if (parsedSearchParams == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtevZaPriznanjePatenta> zahtevi = patentService.getByMetadata(parsedSearchParams, metadataSearchParamsDTO.isSearchForNeobradjeni());
        List<SimpleZahtevDTO> simpleZahtevDTOs = Mapper.mapToSimpleZahtevs(zahtevi);

        return ResponseEntity.ok(simpleZahtevDTOs);
    }

    @PutMapping(path = "/text-search", produces = "application/xml", consumes = "application/xml")
    public ResponseEntity<List<SimpleZahtevDTO>> getZahteviByTextSearch(@RequestBody TextSearchDTO textSearchDTO) throws Exception {
        String stripped = textSearchDTO.getTextSearch().trim();

        if (stripped.trim().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<ZahtevZaPriznanjePatenta> zahtevi = patentService.getByText(stripped, textSearchDTO.isCasesensitive(), textSearchDTO.isSearchForNeobradjeni());

        if (zahtevi == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<SimpleZahtevDTO> simpleZahtevDTOs = Mapper.mapToSimpleZahtevs(zahtevi);

        return ResponseEntity.ok(simpleZahtevDTOs);
    }

    @PostMapping("/file-upload/{godinaPrijave}/{brojPrijave}/{tipPriloga}")
    public ResponseEntity<String> uploadPrilog(@PathVariable("godinaPrijave") String godinaPrijave,
                                               @PathVariable("brojPrijave") String brojPrijave,
                                               @PathVariable("tipPriloga") String tipPriloga,
                                               @RequestParam("file") MultipartFile file) {
        try {
            String brojPrijaveA = godinaPrijave + "/" + brojPrijave;

            if (tipPriloga.trim().equals("")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            boolean isOkay = patentService.addPrilog(brojPrijaveA, tipPriloga, file);

            if (!isOkay) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/save/{brojPrijaveId}-{brojPrijaveGodina}")
    public ResponseEntity<String> saveAfterPrilogAddition(@PathVariable("brojPrijaveId") String brojPrijaveId,
                                                          @PathVariable("brojPrijaveGodina") String brojPrijaveGodina) {
        try {
            String brojPrijave = brojPrijaveId.trim().concat("/").concat(brojPrijaveGodina.trim());

            if (!patentService.saveZahtevAfterPrilogAddition(brojPrijave)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
