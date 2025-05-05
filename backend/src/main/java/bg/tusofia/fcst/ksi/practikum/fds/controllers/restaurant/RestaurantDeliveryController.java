package bg.tusofia.fcst.ksi.practikum.fds.controllers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.deliveries.CreateDeliveryRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.deliveries.EditDeliveryRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.delivery.DeliveryResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.DeliveryToProduct;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery.DeliveryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.delivery.DeliveryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantDeliveryService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantProductService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/{restaurantId}/deliveries")
public class RestaurantDeliveryController
        extends BaseRestController<
            Delivery,
            CreateDeliveryRequest,
            EditDeliveryRequest,
            DeliveryResponse,
            RestaurantDeliveryService,
            DeliveryJpaRepository,
            DeliveryPagingRepository
        > {
    private final RestaurantProductService productService;

    public RestaurantDeliveryController(RestaurantDeliveryService service, ModelMapper modelMapper, RestaurantService restaurantService, RestaurantProductService productService) {
        super(
                service,
                modelMapper,
                Delivery.class,
                DeliveryResponse.class,
                "/api/v1/resources/{restaurantId}/deliveries",
                List.of(restaurantService)
        );
        this.productService = productService;
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
    public ResponseEntity<?> createResource(CreateDeliveryRequest createResourceDto, HttpServletRequest request) {
        return super.createResource(createResourceDto, request);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> editResource(Long id, EditDeliveryRequest editResourceDto, HttpServletRequest request) {
        return super.editResource(id, editResourceDto, request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchResource(@PathVariable  Long id, HttpServletRequest request) {
        List<Object> parentResources = preAuthorize(request);

        return this.generateResponse(service.registerCourier(id, request, parentResources));
    }


    @Override
    protected Delivery mapFromCreateDto(CreateDeliveryRequest createResourceDto, List<Object> parentResources) {
        Restaurant restaurant = (Restaurant) parentResources.getFirst();
        Delivery delivery = new Delivery();
        service.registerRelations(
                () -> null,
                DeliveryToProduct::generate,
                (ids) -> productService.getResourcesByIds(ids, parentResources),
                delivery,
                createResourceDto.getProductIds(),
                delivery::setProducts
        );
        delivery.setDeliveryStatus(DeliveryStatus.PREPARING);
        delivery.setRestaurant(restaurant);
        return delivery;
    }
}
