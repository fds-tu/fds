package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.NotResourceCreatorException;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

public abstract class RestaurantOwnerAuthorizer<R> extends BaseAuthorizer<R> {
    public RestaurantOwnerAuthorizer(AuthService authService) {
        super(authService);
    }

    public User verifyRestaurantOwner(List<Object> parentResources, ResourceAccessType accessType, Restaurant resource, HttpServletRequest request) {
        User currentUser = authService.getCurrentUser(request);
        if (!(List.of(ResourceAccessType.GET_SPECIFIC, ResourceAccessType.GET_ALL).contains(accessType)) && resource.getRoles().stream().noneMatch((r) -> Objects.equals(r.getPrimary().getId(), currentUser.getId()) && r.getRestaurantAccessType().equals(RestaurantAccessType.ADMIN))) {
            throw new NotResourceCreatorException("Restaurant", "Id", resource.getId().toString());
        }

        return currentUser;
    }
}
