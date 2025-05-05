package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class InvalidResourceCreationException extends FdsRestException {
    public InvalidResourceCreationException(String resourceName) {
        super("InvalidResourceCreationException", String.format("You cannot create this %s", resourceName), HttpStatus.FORBIDDEN, null);
    }
}
