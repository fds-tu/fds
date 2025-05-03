package bg.tusofia.fcst.ksi.practikum.fds.authorizers.category;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.OnlyGetAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import bg.tusofia.fcst.ksi.practikum.fds.services.authentication.AuthService;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceAuthorizer extends OnlyGetAuthorizer<Category> {
    public CategoryServiceAuthorizer(AuthService authService) {
        super(authService);
    }
}
