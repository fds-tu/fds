package bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<Restaurant, Long> {

}
