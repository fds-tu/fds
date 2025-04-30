package bg.tusofia.fcst.ksi.practikum.fds.repositories.product;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Product findFirstByRestaurant_IdAndId(Long restaurantId, Long id);
}
