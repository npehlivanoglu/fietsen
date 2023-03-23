package be.vdab.fietsen.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class IndividueleCursus extends Cursus {
    private int duurtijd;

    public int getDuurtijd() {
        return duurtijd;
    }

    public void setDuurtijd(int duurtijd) {
        this.duurtijd = duurtijd;
    }
}
