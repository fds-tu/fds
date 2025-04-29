package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class NotResourceCreatorException extends FdsRestException {
    public NotResourceCreatorException(String resourceName, String fieldName, String fieldValue) {
        super(
                "NotResourceCreatorException",
                String.format("You are not the creator of this %s with %s %s and cannot manipulate it.", resourceName, fieldName, fieldValue),
                HttpStatus.FORBIDDEN,
                null
        );
    }
}
