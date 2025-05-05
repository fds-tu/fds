package bg.tusofia.fcst.ksi.practikum.fds.authorizers.base;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceAccessTypeException;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class OnlyGetAuthorizer<R> extends BaseAuthorizer<R> {
    public OnlyGetAuthorizer(AuthService authService) {
        super(authService);
    }

    @Override
    public User authorize(List<Object> parentResources, ResourceAccessType accessType, R resource, HttpServletRequest request) {
        if(!(List.of(ResourceAccessType.GET_ALL, ResourceAccessType.GET_SPECIFIC).contains(accessType))) {
            throw new InvalidResourceAccessTypeException("resource", accessType);
        }

        return super.authorize(parentResources, accessType, resource, request);
    }
}
