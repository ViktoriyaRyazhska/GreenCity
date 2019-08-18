package greencity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadEmailException extends RuntimeException {
    public BadEmailException(String message) {
        super(message);
    }
}