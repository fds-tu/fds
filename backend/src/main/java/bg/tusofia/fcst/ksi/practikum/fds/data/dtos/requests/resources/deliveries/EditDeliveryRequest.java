package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.deliveries;

import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.validators.enums.EnumValid;
import lombok.Data;

@Data
public class EditDeliveryRequest {
    @EnumValid(message = "Delivery status must be a valid delivery status", enumClass = DeliveryStatus.class)
    private DeliveryStatus deliveryStatus;
}
