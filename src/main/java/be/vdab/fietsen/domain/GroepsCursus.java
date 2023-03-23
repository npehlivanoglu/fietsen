package be.vdab.fietsen.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("G")
public class GroepsCursus extends Cursus {
    private LocalDate van;
    private LocalDate tot;

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }

    public void setVan(LocalDate van) {
        this.van = van;
    }

    public void setTot(LocalDate tot) {
        this.tot = tot;
    }
}
