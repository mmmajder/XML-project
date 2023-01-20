package com.example.autorskapravabackend.service;

import com.example.autorskapravabackend.dto.DetaljiOZahtevu;
import com.example.autorskapravabackend.dto.ObradaZahteva;
import com.example.autorskapravabackend.dto.ZahtevDTO;
import com.example.autorskapravabackend.model.ResenjeZahteva;
import com.example.autorskapravabackend.model.ZahtevZaAutorskaPrava;
import com.example.autorskapravabackend.repository.ResenjeZahtevaRepository;
import com.example.autorskapravabackend.utils.Utils;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

@Service
public class ResenjeService {
    private final ResenjeZahtevaRepository repository = new ResenjeZahtevaRepository();
    private final AutorskaPravaService service;

    public ResenjeService(AutorskaPravaService service) {
        this.service = service;
    }

    public DetaljiOZahtevu getResenjeZahteva(String brojPrijave) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResenjeZahteva resenje = repository.dobaviPoBrojuPrijave(brojPrijave);
        ZahtevZaAutorskaPrava zahtevZaAutorskaPrava = service.getZahtev(brojPrijave);

        ZahtevDTO zahtevDTO = ZahtevDTO.builder()
                .datumPodnosenja(Utils.formatDate(zahtevZaAutorskaPrava.getInformacijeOZahtevu().getDatumPodnosenja()))
                .brojPrijave(brojPrijave)
                .build();
        ObradaZahteva obrada = ObradaZahteva.builder()
                .sluzbenik(resenje.getSluzbenik())
                .datumObrade(Utils.formatDate(resenje.getDatumObrade()))
                .odbijen(resenje.isOdbijen())
                .razlogOdbijanja(resenje.getRazlogOdbijanja())
                .sifra(resenje.getSifra())
                .build();
        return DetaljiOZahtevu.builder()
                .obrada(obrada)
                .zahtev(zahtevDTO)
                .build();
    }
}
