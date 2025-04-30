package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.NotResourceCreatorException;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RestaurantServiceAuthorizer extends BaseAuthorizer<Restaurant> {
    public RestaurantServiceAuthorizer(AuthService authService) {
        super(authService);
    }

    @Override
    public User authorize(List<Object> parentResources, ResourceAccessType accessType, Restaurant resource, HttpServletRequest request) {
        User currentUser = super.authorize(parentResources, accessType, resource, request);

        if (List.of(ResourceAccessType.EDIT_SPECIFIC, ResourceAccessType.DELETE_SPECIFIC).contains(accessType) && resource.getRoles().stream().noneMatch((r) -> Objects.equals(r.getPrimary().getId(), currentUser.getId()))) {
            throw new NotResourceCreatorException("Restaurant", "Id", resource.getId().toString());
        }

        return currentUser;
    }
}
