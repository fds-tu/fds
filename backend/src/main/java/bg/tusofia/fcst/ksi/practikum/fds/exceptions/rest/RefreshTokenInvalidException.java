package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class RefreshTokenInvalidException extends FdsRestException {
    public RefreshTokenInvalidException() {
        super("RefreshTokenInvalidException", "Refresh token reuse detected — session terminated", HttpStatus.FORBIDDEN, null);
    }
}
