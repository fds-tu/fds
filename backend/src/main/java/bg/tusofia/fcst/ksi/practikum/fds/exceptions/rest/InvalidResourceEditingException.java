package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class InvalidResourceEditingException extends FdsRestException {
    public InvalidResourceEditingException(String resourceName) {
        super("InvalidResourceEditingException", String.format("You cannot edit this %s", resourceName), HttpStatus.FORBIDDEN, null);
    }
}
