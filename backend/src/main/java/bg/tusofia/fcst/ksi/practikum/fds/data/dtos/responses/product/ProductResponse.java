package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.product;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.RestaurantProductResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResponse extends RestaurantProductResponse {
    private RestaurantDto restaurant;
}
