package bg.tusofia.fcst.ksi.practikum.fds.authorizers.user;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAuthorizer extends BaseAuthorizer<User> {
    public UserServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
