package be.vdab.fietsen.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("G")
@Access(AccessType.FIELD)
public class GroepsCursus extends Cursus {
    private LocalDate van;
    private LocalDate tot;

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }

}
