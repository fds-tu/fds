package bg.tusofia.fcst.ksi.practikum.fds.services.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.allergen.AllergenServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen.AllergenJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen.AllergenPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AllergenService extends BaseService<Allergen, Long, AllergenJpaRepository, AllergenPagingRepository> {
    public AllergenService(AllergenJpaRepository jpaRepository, AllergenPagingRepository pagingRepository, AllergenServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Allergen");
    }
}
