package bg.tusofia.fcst.ksi.practikum.fds.authorizers.base;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import jakarta.persistence.MappedSuperclass;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@MappedSuperclass
@RequiredArgsConstructor
public abstract class BaseAuthorizer<R> {
    private final AuthService authService;

    public User authorize(List<Object> parentResources, ResourceAccessType accessType, R resource, HttpServletRequest request) {
        return authService.getCurrentUser(request);
    }
}
