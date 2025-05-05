package bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest;

import org.springframework.http.HttpStatus;

public class DeliveryAlreadyTakenException extends FdsRestException {
    public DeliveryAlreadyTakenException() {
        super("DeliveryAlreadyTakenException", "Delivery was already given to another Courier", HttpStatus.FORBIDDEN, null);
    }
}
