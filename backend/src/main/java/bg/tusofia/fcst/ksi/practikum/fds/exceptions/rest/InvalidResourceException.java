package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class InvalidResourceException extends FdsRestException {
    public InvalidResourceException(String resourceName) {
        this(resourceName, null);
    }

    public InvalidResourceException(String resourceName, Serializable data) {
        this(resourceName, data, false);
    }

    public InvalidResourceException(String resourceName, Serializable data, boolean changeExceptionName) {
        super(
                changeExceptionName ? String.format("Invalid%sException", resourceName) : "InvalidResourceException",
                String.format("Invalid %s", resourceName),
                HttpStatus.BAD_REQUEST,
                data
        );
    }
}
