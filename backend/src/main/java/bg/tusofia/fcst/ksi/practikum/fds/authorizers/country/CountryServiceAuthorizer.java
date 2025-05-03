package bg.tusofia.fcst.ksi.practikum.fds.authorizers.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.OnlyGetAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class CountryServiceAuthorizer extends OnlyGetAuthorizer<Country> {
    public CountryServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
