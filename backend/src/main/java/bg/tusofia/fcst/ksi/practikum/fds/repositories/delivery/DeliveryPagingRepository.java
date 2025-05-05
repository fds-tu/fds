package bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeliveryPagingRepository extends PagingAndSortingRepository<Delivery, Long> {
    @Query("SELECT d from Delivery d where d.restaurant.id = ?1 and d.courier is null")
    List<Delivery> findAllByRestaurant_IdAndActive(Long restaurantId, Pageable pageable);

    @Query("SELECT d from Delivery d where d.restaurant.id = ?1 and d.courier.id = ?2")
    List<Delivery> findAllByRestaurant_IdAndMine(Long restaurantId, Long userId, Pageable pageable);
}
