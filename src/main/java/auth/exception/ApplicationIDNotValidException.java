package auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApplicationIDNotValidException extends RuntimeException {

    public ApplicationIDNotValidException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("The application_id is not vaild");
    }
}
