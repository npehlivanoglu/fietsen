package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EenAndereGebruikerWijzijdeDeDocentException extends RuntimeException {
    public EenAndereGebruikerWijzijdeDeDocentException() {
        super("Een andere gebuiker wijzigde de docent");
    }
}
