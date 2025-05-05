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
                modelMapper,
                Product.class,
                RestaurantProductResponse.class,
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

    @Override
    protected Product mapFromCreateDto(CreateProductRequest createResourceDto, List<Object> parentResources) {
        Product resource = this.mapper.mapToResource(createResourceDto);
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
        return resource;
    }

    @Override
    protected Product mapFromEditDto(Product resource, EditProductRequest editResourceDto) {
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
    }
}
