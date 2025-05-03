package bg.tusofia.fcst.ksi.practikum.fds.repositories.category;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
}
