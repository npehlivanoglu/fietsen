package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.*;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.exceptions.EenAndereGebruikerWijzijdeDeDocentException;
import be.vdab.fietsen.services.DocentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("docenten")
class DocentContoller {
    private final DocentService docentService;

    private record Opslag(@NotNull @Positive BigDecimal bedrag) {
    }

    ;

    private record NieuweBijnaam(@NotBlank String bijnaam) {
    }

    private record DocentBeknopt(long id, String voornaam, String familienaam) {
        DocentBeknopt(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam());
        }
    }

    private record DocentBeknoptMetBijnamen(long id, String voornaam, String familienaam, Set<String> bijnamen) {
        DocentBeknoptMetBijnamen(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam(), docent.getBijnamen());
        }
    }

    DocentContoller(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }

    @GetMapping
    Stream<DocentBeknopt> findAll() {
        return docentService.findAll().stream().map(docent -> new DocentBeknopt(docent));
    }

    @GetMapping("{id}")
    Docent findById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping("{id}/bestaat")
    boolean bestaatById(@PathVariable long id) {
        return docentService.existsById(id);
    }

    @PostMapping
    long create(@RequestBody @Valid NieuweDocent nieuweDocent) {
        return docentService.create(nieuweDocent);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        try {
            docentService.delete(id);
        } catch (EmptyStackException ignored) {
        }
    }

    @PatchMapping("{id}/wedde")
    void wijzigWedde(@PathVariable long id, @RequestBody @Valid GewijzigdeWedde gewijzigdeWedde) {
        try {
            docentService.wijzigWedde(id, gewijzigdeWedde.wedde());
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new EenAndereGebruikerWijzijdeDeDocentException();
        }

    }

    @GetMapping(params = "wedde")
    Stream<DocentBeknopt> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde)
                .stream()
                .map(docent -> new DocentBeknopt(docent));
    }

    @GetMapping(params = "emailAdres")
    Docent findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping("metGrootsteWedde")
    Stream<DocentBeknopt> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde()
                .stream()
                .map(docent -> new DocentBeknopt(docent));
    }

    @GetMapping("weddes/grootste")
    BigDecimal findGrootsteWedde() {
        return docentService.findGrootsteWedde();
    }

    @GetMapping("namen")
    List<EnkelNaam> findNamen() {
        return docentService.findNamen();
    }

    @GetMapping("aantalPerWedde")
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentService.findAantalDocentenPerWedde();
    }

    @PostMapping("weddeverhogingen")
    void algemeneOpslag(@RequestBody @Valid Opslag opslag) {
        docentService.algemeneOpslag(opslag.bedrag());
    }

    @PostMapping("{id}/bijnamen")
    void voegBijnaamToe(@PathVariable long id, @RequestBody @Valid NieuweBijnaam nieuweBijnaam) {
        docentService.voegBijnaamToe(id, nieuweBijnaam.bijnaam());
    }

    @DeleteMapping("{id}/bijnamen/{bijnaam}")
    void verwijderBijnaam(@PathVariable long id, @PathVariable String bijnaam) {
        docentService.verwijderBijnaam(id, bijnaam);
    }


    @GetMapping("{id}/emailAdres")
    String findEmailAdresById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getEmailAdres();
    }

    @GetMapping("metBijnamen")
    Stream<DocentBeknoptMetBijnamen> findAllMetBijnamen() {
        return docentService.findAllMetBijnamen()
                .stream()
                .map(docent -> new DocentBeknoptMetBijnamen(docent));
    }

}
