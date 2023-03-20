package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.*;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.exceptions.EenAndereGebruikerWijzijdeDeDocentException;
import be.vdab.fietsen.services.DocentService;
import jakarta.validation.Valid;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.List;

@RestController
@RequestMapping("docenten")
class DocentContoller {
    private final DocentService docentService;

    DocentContoller(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }

    @GetMapping
    List<Docent> findAll() {
        return docentService.findAll();
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
    List<Docent> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde);
    }

    @GetMapping(params = "emailAdres")
    Docent findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping("metGrootsteWedde")
    List<Docent> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde();
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
}
