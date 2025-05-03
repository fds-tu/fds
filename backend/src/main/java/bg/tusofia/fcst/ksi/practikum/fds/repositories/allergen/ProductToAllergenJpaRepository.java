package bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.ProductToAllergen;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductToAllergenJpaRepository extends JpaRepository<ProductToAllergen, Long> {
    ProductToAllergen findByPrimary(Product primary);
}
