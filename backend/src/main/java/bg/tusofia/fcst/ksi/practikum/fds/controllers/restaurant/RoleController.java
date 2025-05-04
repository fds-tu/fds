package bg.tusofia.fcst.ksi.practikum.fds.controllers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.roles.CreateRoleRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RoleJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.role.RolePagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RoleService;
import bg.tusofia.fcst.ksi.practikum.fds.services.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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

    public RoleController(RoleService service, ModelMapper modelMapper, UserService userService, RestaurantService restaurantService) {
        super(
                service,
                modelMapper,
                Role.class,
                CreateRoleRequest.class,
                "/api/v1/resources/restaurants/{restaurantId}/roles",
                List.of(restaurantService)
        );
        this.userService = userService;
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<?> createResource(@RequestBody @Valid CreateRoleRequest createResourceDto, HttpServletRequest request) {
        return super.createResource(createResourceDto, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable @Valid Long id, HttpServletRequest request) {
        return super.deleteResource(id, request);
    }

    @Override
    protected Role mapFromCreateDto(CreateRoleRequest createResourceDto) {
        Role role = new Role();
        role.setRestaurantAccessType(createResourceDto.getAccessType());
        role.setPrimary(userService.getResourceById(createResourceDto.getUserId()));
        return role;
    }
}