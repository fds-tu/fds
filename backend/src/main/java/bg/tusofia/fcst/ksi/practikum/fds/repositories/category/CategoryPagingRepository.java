package bg.tusofia.fcst.ksi.practikum.fds.repositories.category;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryPagingRepository extends PagingAndSortingRepository<Category, Long> {
}
