package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class InvalidUrlComponentsException extends FdsRestException {
    public InvalidUrlComponentsException() {
        super(
                "InvalidUrlComponentsException",
                "Number of checker services doesn't match the number of url components",
                HttpStatus.BAD_REQUEST,
                null
        );
    }
}
