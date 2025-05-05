package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import org.springframework.http.HttpStatus;

public class InvalidResourceAccessTypeException extends FdsRestException {
    public InvalidResourceAccessTypeException(String resourceName, ResourceAccessType accessType) {
        super("InvalidResourceAccessType", String.format("You cannot perform this operation (%s) on this %s", resourceName, accessType.toString()), HttpStatus.FORBIDDEN, null);
    }
}
