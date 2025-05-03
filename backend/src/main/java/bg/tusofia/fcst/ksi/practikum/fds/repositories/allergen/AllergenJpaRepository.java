package bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenJpaRepository extends JpaRepository<Allergen, Long> {
}
