package bg.tusofia.fcst.ksi.practikum.fds.authorizers.base;

import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AllAuthorizer<R> extends BaseAuthorizer<R> {
    public AllAuthorizer(AuthService authService) {
        super(authService);
    }
}
