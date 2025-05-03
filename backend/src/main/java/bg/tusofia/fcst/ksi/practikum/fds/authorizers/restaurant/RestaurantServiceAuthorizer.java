package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class RestaurantServiceAuthorizer extends BaseAuthorizer<Restaurant> {
    public RestaurantServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
