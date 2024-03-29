package be.vdab.fietsen.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cursussen")
@DiscriminatorColumn(name = "soort")
public abstract class Cursus {
    @Id
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
