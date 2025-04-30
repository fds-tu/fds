package bg.tusofia.fcst.ksi.practikum.fds.services.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant.ProductServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceDeletionException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceEditingException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantProductService extends BaseService<Product, Long, ProductJpaRepository, ProductPagingRepository> {
    private final RestaurantJpaRepository restaurantJpaRepository;

    public RestaurantProductService(ProductJpaRepository jpaRepository, ProductPagingRepository pagingRepository, ProductServiceAuthorizer authorizer, RestaurantJpaRepository restaurantJpaRepository) {
        super(jpaRepository, pagingRepository, authorizer, "Product");
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    protected Product getResourceInternal(Long resourceId, List<Object> parentResources) {
        return jpaRepository.findFirstByRestaurant_IdAndId(((Restaurant) parentResources.getFirst()).getId(), resourceId);
    }

    @Override
    protected List<Product> getResourcesInternal(int page, int size, String sortBy, List<Object> parentResources) {
        return pagingRepository.findAllByRestaurant_Id(((Restaurant) parentResources.getFirst()).getId(), PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    protected Product createResourceInternal(Product resource, HttpServletRequest request, List<Object> parentResources) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        resource.setRestaurant(restaurant);
        restaurant.addProduct(resource);

        save(resource);
        restaurantJpaRepository.save(restaurant);

        return resource;
    }

    @Override
    protected Product editResourceInternal(Product resource, HttpServletRequest request, List<Object> parentResources) {
        if(!resource.getRestaurant().getId().equals(((Restaurant) parentResources.getFirst()).getId())) {
            throw new InvalidResourceEditingException("Product");
        }

        return super.editResourceInternal(resource, request, parentResources);
    }

    @Override
    protected void deleteResourceInternal(Product resource, List<Object> parentResources) {
        if(!resource.getRestaurant().getId().equals(((Restaurant) parentResources.getFirst()).getId())) {
            throw new InvalidResourceDeletionException("Product");
        }

        super.deleteResourceInternal(resource, parentResources);
    }
}
