package bg.tusofia.fcst.ksi.practikum.fds.authorizers.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.AllAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AllergenServiceAuthorizer extends AllAuthorizer<Allergen> {
    public AllergenServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
