package bg.tusofia.fcst.ksi.practikum.fds.authorizers.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.OnlyGetAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class CountryRegionServiceAuthorizer extends OnlyGetAuthorizer<Region> {
    public CountryRegionServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
