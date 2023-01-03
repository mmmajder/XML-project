package com.example.patentbackend.service;

import com.example.patentbackend.dto.ZahtevZaPriznanjePatentaDTO;
import com.example.patentbackend.mapper.Mapper;
import com.example.patentbackend.model.*;
import com.example.patentbackend.repository.PatentRepository;
import com.example.patentbackend.utils.AuthenticationUtilities;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;


@Service
public class PatentService {

    private PatentRepository patentRepository = new PatentRepository();

    public ZahtevZaPriznanjePatenta getZahtevZaPriznanjePatenta(String brojPrijave) {
        return patentRepository.getZahtevZaPriznanjePatenta(brojPrijave);
    }

    public ZahtevZaPriznanjePatenta createZahtevZaPriznanjePatenta(ZahtevZaPriznanjePatentaDTO zahtevZaPriznanjePatentaDTO) {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = Mapper.mapToZahtevZaPriznanjePatenta(zahtevZaPriznanjePatentaDTO);
        OutputStream marshaledPatent = marshalPatent(zahtevZaPriznanjePatenta);
        savePatentInExistDB(marshaledPatent, zahtevZaPriznanjePatenta.getOsnovneInformacijeOZahtevuZaPriznanjePatenta().getBrojPrijave());
        //TODO RDF save
        return zahtevZaPriznanjePatenta;
    }

    private void savePatentInExistDB(OutputStream marshaledPatent, String brojPrijave) {
        try {
            AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
            Class<?> cl = Class.forName(conn.driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);
            String collectionId = "/db/xws-project/patent";
            String documentId = brojPrijave.split("/")[0] + "-" + brojPrijave.split("/")[1] + ".xml";
            Collection col = getOrCreateCollection(collectionId, conn);
            XMLResource res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
            res.setContent(marshaledPatent);
            col.storeResource(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OutputStream marshalPatent(ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta) {
        try {
            JAXBContext context = JAXBContext.newInstance("com.example.patentbackend.model");
            OutputStream os = new ByteArrayOutputStream();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtevZaPriznanjePatenta, os);
            return os;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static Collection getOrCreateCollection(String collectionUri, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0, conn);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset, AuthenticationUtilities.ConnectionProperties conn) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        // create the collection if it does not exist
        if (col == null) {

            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String pathSegments[] = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset, conn);
        } else {
            return col;
        }
    }


    private ZahtevZaPriznanjePatenta kreirajZahtevZaPriznanjePatenta() {
        ZahtevZaPriznanjePatenta zahtevZaPriznanjePatenta = new ZahtevZaPriznanjePatenta();
        zahtevZaPriznanjePatenta.setOsnovneInformacijeOZahtevuZaPriznanjePatenta(kreirajOsnovneInformacije());
        zahtevZaPriznanjePatenta.setSadrzajZahtevaZaPriznanjePatenta(kreirajSadrzajZahtevaZaPrizanjePatenta());
        return zahtevZaPriznanjePatenta;
    }

    private SadrzajZahtevaZaPriznanjePatenta kreirajSadrzajZahtevaZaPrizanjePatenta() {
        SadrzajZahtevaZaPriznanjePatenta sadrzajZahtevaZaPriznanjePatenta = new SadrzajZahtevaZaPriznanjePatenta();
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPodnosiocuPrijave(kreirajPodatkeOPodnosiocuPrijave());
        sadrzajZahtevaZaPriznanjePatenta.setDostavljanje(kreirajDostavljanje());
        sadrzajZahtevaZaPriznanjePatenta.setNazivPronalaska(kreirajNazivPronalaska());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPrijavi(kreirajPodatkeOPrijavi());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPronalazacu(kreirajPotatkeOPronalazacu());
        sadrzajZahtevaZaPriznanjePatenta.setPodaciOPunomocniku(kreirajPodatkeOPunomocniku());
        sadrzajZahtevaZaPriznanjePatenta.setZahtevZaPriznanjePrvenstvaIzRanijihPrijava(kreirajZahtevZaPriznanjePrvenstva());
        return sadrzajZahtevaZaPriznanjePatenta;
    }

    private List<RanijaPrijava> kreirajZahtevZaPriznanjePrvenstva() {
        RanijaPrijava ranijaPrijava1 = new RanijaPrijava();
        ranijaPrijava1.setBrojRanijePrijave("P-1231/2019");
        ranijaPrijava1.setDatumaPodnosenjaPrijave(new Date(2000, 2, 2));
        ranijaPrijava1.setDvoslovnaOznaka("NS");

        RanijaPrijava ranijaPrijava2 = new RanijaPrijava();
        ranijaPrijava2.setBrojRanijePrijave("P-32312/2019");
        ranijaPrijava2.setDatumaPodnosenjaPrijave(new Date(2000, 5, 2));
        ranijaPrijava2.setDvoslovnaOznaka("NS");

        ArrayList<RanijaPrijava> listaRanijihPrijava = new ArrayList<>();
        listaRanijihPrijava.add(ranijaPrijava1);
        listaRanijihPrijava.add(ranijaPrijava2);
        return listaRanijihPrijava;
    }

    private PodaciOPunomocniku kreirajPodatkeOPunomocniku() {
        TPravnoLice punomocnik = new TPravnoLice();
        Adresa adresa = new Adresa();
        adresa.setBrojUUlici(3);
        adresa.setDrzava("Srbija");
        adresa.setMesto("Novi Sad");
        adresa.setPostanskiBroj(21000);
        adresa.setUlica("Bulevar Evrope");
        punomocnik.setAdresaLica(adresa);
        punomocnik.setBrojFaksa("321321");
        punomocnik.setBrojTelefona("+381 12315466");
        punomocnik.setePosta("Mejlpunomocnika@gmial.com");
        punomocnik.setPoslovnoIme("Sverc komerc");
        PodaciOPunomocniku podaciOPunomocniku = new PodaciOPunomocniku();
        podaciOPunomocniku.setPunomocnik(punomocnik);
        podaciOPunomocniku.setVrstaPunomocnika("PUNOMOCNIK_ZA_PRIJEM_PISMENA");
        podaciOPunomocniku.setZajednickiPredstavnik(false);
        return podaciOPunomocniku;
    }

    private PodaciOPronalazacu kreirajPotatkeOPronalazacu() {
        PodaciOPronalazacu podaciOPronalazacu = new PodaciOPronalazacu();
        podaciOPronalazacu.setPronalazacNeZeliDaBudeNaveden(false);
        TFizickoLice pronalazac = new TFizickoLice();
        pronalazac.setDrzavljanstvo("Srpsko");
        pronalazac.setIme("Pera");
        pronalazac.setPrezime("Peric");
        Adresa adresaLica = new Adresa();
        adresaLica.setBrojUUlici(2);
        adresaLica.setDrzava("Crna Gora");
        adresaLica.setMesto("Niksic");
        adresaLica.setPostanskiBroj(432423);
        adresaLica.setUlica("Bla bla");
        pronalazac.setAdresaLica(adresaLica);
        pronalazac.setBrojFaksa("4234234");
        pronalazac.setBrojTelefona("+381 1651326");
        pronalazac.setePosta("mejlpronalazaca@gmail.com");
        podaciOPronalazacu.setPronalazac(pronalazac);
        return podaciOPronalazacu;
    }

    private PodaciOPrijavi kreirajPodatkeOPrijavi() {
        PodaciOPrijavi podaciOPrijavi = new PodaciOPrijavi();
        podaciOPrijavi.setBrojOsnovnePrijave("P-432/2022");
        podaciOPrijavi.setDatumPodnosenjaPrijave(new Date(2000, 3, 4));
        podaciOPrijavi.setVrstaPrijave("IZDVOJENA");
        return podaciOPrijavi;
    }

    private List<NazivPronalaska> kreirajNazivPronalaska() {
        ArrayList<NazivPronalaska> nazivi = new ArrayList<>();
        NazivPronalaska nazivPronalaska = new NazivPronalaska();
        nazivPronalaska.setJezik("SRPSKI");
        nazivPronalaska.setNaziv("Novi izum");
        NazivPronalaska nazivPronalaska2 = new NazivPronalaska();
        nazivPronalaska2.setJezik("ENGLESKI");
        nazivPronalaska2.setNaziv("New invention");
        nazivi.add(nazivPronalaska);
        nazivi.add(nazivPronalaska2);
        return nazivi;
    }

    private Dostavljanje kreirajDostavljanje() {
        Dostavljanje dostavljanje = new Dostavljanje();
        dostavljanje.setNacinDostave("PAPIRNA_FORM");
        return dostavljanje;
    }

    private PodaciOPodnosiocuPrijave kreirajPodatkeOPodnosiocuPrijave() {
        TFizickoLice podnosilacPrijave = new TFizickoLice();
        podnosilacPrijave.setDrzavljanstvo("Srpsko");
        podnosilacPrijave.setIme("Zika");
        podnosilacPrijave.setPrezime("Zikic");
        Adresa adresa = new Adresa();
        adresa.setBrojUUlici(3);
        adresa.setDrzava("Srbija");
        adresa.setMesto("Novi Sad");
        adresa.setPostanskiBroj(21000);
        adresa.setUlica("Bulevar Oslobodjenja");
        podnosilacPrijave.setAdresaLica(adresa);
        podnosilacPrijave.setBrojFaksa("33232121321");
        podnosilacPrijave.setBrojTelefona("+381 1231434");
        podnosilacPrijave.setePosta("Mejlmejl@gmial.com");
        PodaciOPodnosiocuPrijave podaciOPodnosiocuPrijave = new PodaciOPodnosiocuPrijave();
        podaciOPodnosiocuPrijave.setPodnosilacPrijave(podnosilacPrijave);
        podaciOPodnosiocuPrijave.setPodnosilacPrijaveJeIPronalazac(false);
        return podaciOPodnosiocuPrijave;
    }

    private OsnovneInformacijeOZahtevuZaPriznanjePatenta kreirajOsnovneInformacije() {
        OsnovneInformacijeOZahtevuZaPriznanjePatenta osnovneInformacijeOZahtevuZaPriznanjePatenta = new OsnovneInformacijeOZahtevuZaPriznanjePatenta();
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setBrojPrijave("P-1314/2019");
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setDatumPrijema(new Date(2000, 1, 1));
        osnovneInformacijeOZahtevuZaPriznanjePatenta.setPriznatiDatumPodnosenja(new Date(2000, 4, 5));
        return osnovneInformacijeOZahtevuZaPriznanjePatenta;
    }

}
