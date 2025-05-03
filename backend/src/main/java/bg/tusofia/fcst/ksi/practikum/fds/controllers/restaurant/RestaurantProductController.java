package bg.tusofia.fcst.ksi.practikum.fds.controllers.restaurant;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.CreateProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.EditProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.RestaurantProductResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.ProductToAllergen;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.ProductToCategory;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.allergen.AllergenService;
import bg.tusofia.fcst.ksi.practikum.fds.services.category.CategoryService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantProductService;
import bg.tusofia.fcst.ksi.practikum.fds.services.restaurant.RestaurantService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
            RestaurantProductResponse,
            RestaurantProductService,
            ProductJpaRepository,
            ProductPagingRepository
        > {
    private final AllergenService allergenService;
    private final CategoryService categoryService;

    public RestaurantProductController(RestaurantProductService service, ModelMapper modelMapper, RestaurantService restaurantService, AllergenService allergenService, CategoryService categoryService) {
        super(
                service,
                new BaseMapper<>(modelMapper, Product.class, RestaurantProductResponse.class),
                "/api/v1/resources/restaurants/{restaurantId}/products",
                List.of(restaurantService)
        );
        this.allergenService = allergenService;
        this.categoryService = categoryService;
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
        Product resource = this.mapper.mapFromCreateDto(createResourceDto);
        service.registerRelations(
                () -> null,
                ProductToAllergen::generate,
                allergenService::getResourcesByIds,
                resource,
                createResourceDto.getAllergenIds(),
                resource::setProductToAllergens
        );
        service.registerRelations(
                () -> null,
                ProductToCategory::generate,
                categoryService::getResourcesByIds,
                resource,
                createResourceDto.getCategoryIds(),
                resource::setProductToCategories
        );
        RestaurantProductResponse response = this.mapper.map(service.createResource(resource, request, preAuthorize(request)));
        return this.generateResponse(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> editResource(Long id, EditProductRequest editResourceDto, HttpServletRequest request) {
        service.editResource(id, (resource) -> {
            service.registerRelations(
                    resource::removeAllAllergen,
                    ProductToAllergen::generate,
                    allergenService::getResourcesByIds,
                    resource,
                    editResourceDto.getAllergenIds(),
                    resource::addAllergens
            );
            service.registerRelations(
                    resource::removeAllCategories,
                    ProductToCategory::generate,
                    categoryService::getResourcesByIds,
                    resource,
                    editResourceDto.getCategoryIds(),
                    resource::addCategories
            );
            return this.mapper.map(resource, editResourceDto);
        }, request, preAuthorize(request));

        return this.generateResponse(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteResource(Long id, HttpServletRequest request) {
        return super.deleteResource(id, request);
    }
}
