package bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, Long> {
    Optional<Delivery> findFirstByRestaurant_IdAndId(Long restaurantId, Long id);
}
