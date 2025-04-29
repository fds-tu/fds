package bg.tusofia.fcst.ksi.practikum.fds.controllers;


import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.restaurants.CreateRestaurantRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.restaurants.EditRestaurantRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.RestaurantResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.restaurant.RestaurantPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.RestaurantService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resources/restaurants")
public class RestaurantController
        extends BaseRestController<
            Restaurant,
            CreateRestaurantRequest,
            EditRestaurantRequest,
            RestaurantResponse,
            RestaurantService,
            RestaurantJpaRepository,
            RestaurantPagingRepository
        > {

    public RestaurantController(RestaurantService service, ModelMapper mapper) {
        super(
            service,
            new BaseMapper<>(mapper, Restaurant.class, RestaurantResponse.class)
        );
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> getResourceById(Long id, HttpServletRequest request) {
        return super.getResourceById(id, request);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<?> getAllResources(Integer page, HttpServletRequest request) {
        return super.getAllResources(page, request);
    }

    @PostMapping("/")
    @Override
    public ResponseEntity<?> createResource(CreateRestaurantRequest createResourceDto, HttpServletRequest request) {
        return super.createResource(createResourceDto, request);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> editResource(Long id, EditRestaurantRequest editResourceDto, HttpServletRequest request) {
        return super.editResource(id, editResourceDto, request);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteResource(Long id, HttpServletRequest request) {
        return super.deleteResource(id, request);
    }
}
