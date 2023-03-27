package be.vdab.fietsen.controllers;

import be.vdab.fietsen.dto.CampusBeknopt;
import be.vdab.fietsen.dto.DocentBeknopt;
import be.vdab.fietsen.exceptions.CampusNietGevondenException;
import be.vdab.fietsen.services.CampusService;
import be.vdab.fietsen.services.DocentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("campussen")
public class CampusController {
    private final CampusService campusService;
    private final DocentService docentService;

    public CampusController(CampusService campusService, DocentService docentService) {
        this.campusService = campusService;
        this.docentService = docentService;
    }

    @GetMapping("westvlaams")
    Stream<CampusBeknopt> findWestVlaamse() {
        return campusService.findWestVlaamse()
                .stream()
                .map(campus -> new CampusBeknopt(campus));
    }

  @GetMapping("{id}/docenten")
    Stream<DocentBeknopt> findDocentenVan(@PathVariable long id) {
        return campusService.findById(id)
                .orElseThrow(CampusNietGevondenException::new)
                .getDocenten()
                .stream()
                .map(DocentBeknopt::new);
    }

/*    @GetMapping("{id}/docenten")
    Stream<DocentBeknopt> findDocentenVan(@PathVariable long id) {
        return docentService.findByCampus_Id(id)
                .stream()
                .map(DocentBeknopt::new);}*/
}
