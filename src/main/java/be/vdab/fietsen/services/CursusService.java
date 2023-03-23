package be.vdab.fietsen.services;

import be.vdab.fietsen.domain.Cursus;
import be.vdab.fietsen.domain.GroepsCursus;
import be.vdab.fietsen.domain.IndividueleCursus;
import be.vdab.fietsen.repositories.CursusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursusService {
    private final CursusRepository cursusRepository;

    public CursusService(CursusRepository cursusRepository) {
        this.cursusRepository = cursusRepository;
    }

    public List<Cursus> findAll() {
        return cursusRepository.findAll();
    }


    public List<GroepsCursus> findGroepsCursussen() {
        return cursusRepository.findGroupsCurssussen();
    }

    public List<IndividueleCursus> findIndividueleCurssussen() {
        return cursusRepository.findIndividueleCursussen();
    }
}
