package bg.tusofia.fcst.ksi.practikum.fds.controllers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.roles.CreateRoleRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RoleJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RolePagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RoleService;
import bg.tusofia.fcst.ksi.practikum.fds.services.user.UserService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/restaurants/{restaurantId}/roles")
public class RoleController
        extends BaseRestController<
        Role,
        CreateRoleRequest,
        CreateRoleRequest,
        CreateRoleRequest,
        RoleService,
        RoleJpaRepository,
        RolePagingRepository
                > {
    private final UserService userService;
    private final RestaurantService restaurantService;


    public RoleController(RoleService service, ModelMapper modelMapper, UserService userService, RestaurantService restaurantService) {
        super(
                service,
                new BaseMapper<>(modelMapper, Role.class, CreateRoleRequest.class),
                "/api/v1/resources/restaurants/{restaurantId}/roles",
                List.of(restaurantService)
        );
        this.userService = userService;
        this.restaurantService = restaurantService;
    }


    @PostMapping("/")
    public ResponseEntity<?> createResource(@RequestBody @Valid CreateRoleRequest createResourceDto, HttpServletRequest request) {
        List<Object> parentResources = preAuthorize(request);

        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        Role newRole = new Role();

        newRole.setRestaurantAccessType(createResourceDto.getAccessType());
        newRole.setPrimary(userService.getResourceById(createResourceDto.getUserId()));
        newRole.setSecondary(restaurant);

        service.createResource(newRole, request, parentResources);
        restaurantService.addRole(restaurant, newRole);

        return this.generateResponse(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable @Valid Long id, HttpServletRequest request) {
        return super.deleteResource(id, request);
    }
}