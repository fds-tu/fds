package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import org.springframework.http.HttpStatus;

public class InvalidResourceAccessTypeException extends FdsRestException {
    public InvalidResourceAccessTypeException(ResourceAccessType accessType) {
        super("InvalidResourceAccessType", String.format("You cannot perform this operation (%s) on this resource", accessType.toString()), HttpStatus.FORBIDDEN, null);
    }
}
