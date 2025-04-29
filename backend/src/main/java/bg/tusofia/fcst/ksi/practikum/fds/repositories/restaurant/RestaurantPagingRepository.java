package bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantPagingRepository extends PagingAndSortingRepository<Restaurant, Long> {
}
