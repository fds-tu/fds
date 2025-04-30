package bg.tusofia.fcst.ksi.practikum.fds.controllers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.CreateProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.EditProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.ProductResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantProductService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/restaurants/{restaurantId}/products")
public class RestaurantProductController
        extends BaseRestController<
        Product,
        CreateProductRequest,
        EditProductRequest,
        ProductResponse,
        RestaurantProductService,
        ProductJpaRepository,
        ProductPagingRepository
        > {

    public RestaurantProductController(RestaurantProductService service, ModelMapper modelMapper, RestaurantService restaurantService) {
        super(
                service,
                new BaseMapper<>(modelMapper, Product.class, ProductResponse.class),
                "/api/v1/resources/restaurants/{restaurantId}/products",
                List.of(restaurantService)
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
    public ResponseEntity<?> createResource(CreateProductRequest createResourceDto, HttpServletRequest request) {
        return super.createResource(createResourceDto, request);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> editResource(Long id, EditProductRequest editResourceDto, HttpServletRequest request) {
        return super.editResource(id, editResourceDto, request);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteResource(Long id, HttpServletRequest request) {
        return super.deleteResource(id, request);
    }
}
