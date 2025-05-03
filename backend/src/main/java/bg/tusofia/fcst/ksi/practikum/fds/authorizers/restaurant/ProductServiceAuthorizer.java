package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceAuthorizer extends RestaurantOwnerAuthorizer<Product> {
    public ProductServiceAuthorizer(AuthService authService) {
        super(authService);
    }

    @Override
    public User authorize(List<Object> parentResources, ResourceAccessType accessType, Product resource, HttpServletRequest request) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        return this.verifyRestaurantOwner(parentResources, accessType, restaurant, request);
    }
}
