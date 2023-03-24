package be.vdab.fietsen.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("I")
@Access(AccessType.FIELD)
public class IndividueleCursus extends Cursus {
    private int duurtijd;

    public int getDuurtijd() {
        return duurtijd;
    }

}
