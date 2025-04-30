package bg.tusofia.fcst.ksi.practikum.fds.services.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.restaurant.RestaurantServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RoleJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class RestaurantService extends BaseService<Restaurant, Long, RestaurantJpaRepository, RestaurantPagingRepository> {
    private final RoleJpaRepository roleJpaRepository;

    public RestaurantService(RestaurantServiceAuthorizer authorizer, RestaurantJpaRepository jpaRepository, RestaurantPagingRepository pagingRepository, RoleJpaRepository roleJpaRepository) {
        super(jpaRepository, pagingRepository, authorizer, "Restaurant");
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    protected Restaurant onCreateResource(Restaurant resource, User user) {
        Role role = new Role();
        role.setRestaurantAccessType(RestaurantAccessType.ADMIN);
        role.setPrimary(user);
        role.setSecondary(resource);
        role.setCreatedDate(Timestamp.from(Instant.now()));
        roleJpaRepository.saveAndFlush(role);

        resource.addRole(role);

        jpaRepository.saveAndFlush(resource);
        return resource;
    }

    public void addRole(Restaurant restaurant, Role newRole) {
        restaurant.addRole(newRole);
        save(restaurant);
    }
}
