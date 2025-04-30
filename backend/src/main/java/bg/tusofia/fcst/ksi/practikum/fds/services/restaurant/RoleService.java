package bg.tusofia.fcst.ksi.practikum.fds.services.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.BaseAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceDeletionException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RoleJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RolePagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<Role, Long, RoleJpaRepository, RolePagingRepository> {
    private final RestaurantJpaRepository restaurantJpaRepository;

    public RoleService(RoleJpaRepository jpaRepository, RolePagingRepository pagingRepository, BaseAuthorizer<Role> authorizer, RestaurantJpaRepository restaurantJpaRepository) {
        super(jpaRepository, pagingRepository, authorizer, "Role");
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Override
    protected void deleteResourceInternal(Role resource, List<Object> parentResources) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();

        if(!resource.getSecondary().getId().equals(restaurant.getId())) {
            throw new InvalidResourceDeletionException(this.resourceName);
        }

        restaurant.removeRole(getResourceById(resource.getId()));

        restaurantJpaRepository.save(restaurant);

        super.deleteResourceInternal(resource, parentResources);
    }
}
