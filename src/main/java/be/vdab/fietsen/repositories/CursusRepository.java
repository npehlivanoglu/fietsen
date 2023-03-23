package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Cursus;
import be.vdab.fietsen.domain.GroepsCursus;
import be.vdab.fietsen.domain.IndividueleCursus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CursusRepository extends JpaRepository<Cursus, Long> {
    @Query("select g from GroepsCursus g")
    List<GroepsCursus> findGroupsCurssussen();

    @Query("select i from IndividueleCursus i")
    List<IndividueleCursus> findIndividueleCursussen();
}
