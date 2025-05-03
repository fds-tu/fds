package bg.tusofia.fcst.ksi.practikum.fds.repositories.product;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAllByRestaurant_Id(Long restaurantId, Pageable pageable);
}
