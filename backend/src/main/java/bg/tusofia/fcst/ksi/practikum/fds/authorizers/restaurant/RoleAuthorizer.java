package bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.FdsRestException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceDeletionException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.NotResourceCreatorException;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RoleAuthorizer extends BaseAuthorizer<Role> {
    public RoleAuthorizer(AuthService authService) {
        super(authService);
    }

    @Override
    public User authorize(List<Object> parentResources, ResourceAccessType accessType, Role resource, HttpServletRequest request) {
        User user = super.authorize(parentResources, accessType, resource, request);
        Restaurant restaurant = (Restaurant) parentResources.getFirst();

        if(List.of(ResourceAccessType.CREATE, ResourceAccessType.DELETE_SPECIFIC).contains(accessType) && restaurant.getRoles().stream().noneMatch(r -> Objects.equals(r.getPrimary().getId(),user.getId()))) {
            throw new NotResourceCreatorException("Restaurant", "Id", restaurant.getId().toString());
        }

        if(ResourceAccessType.CREATE.equals(accessType) && restaurant.getRoles().stream().anyMatch(r -> r.getPrimary().getId().equals(resource.getPrimary().getId()))) {
            throw new FdsRestException("UserAlreadyHasRoleInRestaurantException", String.format("User with Id %s is already a part of this restaurant", resource.getPrimary().getId().toString()), HttpStatus.BAD_REQUEST, null);
        }

        if(ResourceAccessType.DELETE_SPECIFIC.equals(accessType) && (resource.getPrimary().getId().equals(user.getId()) || restaurant.getRoles().stream().noneMatch(r -> r.getPrimary().getId().equals(resource.getPrimary().getId())))) {
            throw new InvalidResourceDeletionException("Role");
        }

        return user;
    }
}
