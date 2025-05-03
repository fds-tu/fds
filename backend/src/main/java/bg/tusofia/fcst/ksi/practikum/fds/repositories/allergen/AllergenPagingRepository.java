package bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AllergenPagingRepository extends PagingAndSortingRepository<Allergen, Long> {
}
