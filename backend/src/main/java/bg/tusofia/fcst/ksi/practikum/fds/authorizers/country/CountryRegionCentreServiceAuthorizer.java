package bg.tusofia.fcst.ksi.practikum.fds.authorizers.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.OnlyGetAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class CountryRegionCentreServiceAuthorizer extends OnlyGetAuthorizer<PopulationCentre> {
    public CountryRegionCentreServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
