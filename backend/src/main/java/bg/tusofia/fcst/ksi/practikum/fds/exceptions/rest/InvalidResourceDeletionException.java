package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class InvalidResourceDeletionException extends FdsRestException {
    public InvalidResourceDeletionException(String resourceName) {
        super("InvalidResourceDeletionException", String.format("You cannot delete this %s", resourceName), HttpStatus.FORBIDDEN, null);
    }
}
