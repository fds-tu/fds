package bg.tusofia.fcst.ksi.practikum.fds.services.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant.ProductServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery.DeliveryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery.DeliveryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantDeliveryService extends BaseService<Delivery, Long, DeliveryJpaRepository, DeliveryPagingRepository> {
    public RestaurantDeliveryService(DeliveryJpaRepository jpaRepository, DeliveryPagingRepository pagingRepository, ProductServiceAuthorizer.RestaurantDeliveryServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Delivery");
    }

    @Override
    protected Delivery getResourceInternal(Long resourceId, List<Object> parentResources) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        return jpaRepository.findFirstByRestaurant_IdAndId(restaurant.getId(), resourceId).orElseThrow(() -> new ResourceNotFoundException(this.resourceName, "Id", resourceId.toString()));
    }

    @Override
    protected List<Delivery> getResourcesInternal(int page, int size, String sortBy, List<Object> parentResources) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        return pagingRepository.findAllByRestaurant_IdAndActive(restaurant.getId(), PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Override
    protected List<Delivery> getMyResourcesInternal(int page, int size, String sortBy, List<Object> parentResources, User user) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        return pagingRepository.findAllByRestaurant_IdAndMine(restaurant.getId(), user.getId(), PageRequest.of(page, size));
    }

    @Override
    protected Delivery createResourceInternal(Delivery resource, HttpServletRequest request, List<Object> parentResources) {
        return resource;
    }

    @Override
    protected Delivery onCreateResource(Delivery resource, User user) {
        resource.setCustomer(user);
        save(resource);

        return resource;
    }

    public Delivery registerCourier(Long id, HttpServletRequest request, List<Object> parentResources) {
        Delivery delivery = getResourceInternal(id, parentResources);

        User currentUser = authorizer.authorize(parentResources, ResourceAccessType.PATCH_SPECIFIC, delivery, request);

        delivery.setCourier(currentUser);
        save(delivery);
        return delivery;
    }
}
