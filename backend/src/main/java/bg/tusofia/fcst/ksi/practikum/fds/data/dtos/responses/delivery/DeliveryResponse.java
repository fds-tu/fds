package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.delivery;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.RestaurantProductResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.users.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class DeliveryResponse {
    private List<RestaurantProductResponse> products;
    private UserDto courier;
    private String deliveryStatus;
}
