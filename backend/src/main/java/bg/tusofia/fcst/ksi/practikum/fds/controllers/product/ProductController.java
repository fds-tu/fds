package bg.tusofia.fcst.ksi.practikum.fds.controllers.product;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.CreateProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products.EditProductRequest;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.product.ProductResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.product.ProductService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resources/products")
public class ProductController
        extends BaseRestController<
            Product,
            CreateProductRequest,
            EditProductRequest,
            ProductResponse,
            ProductService,
            ProductJpaRepository,
            ProductPagingRepository
        >  {

    public ProductController(ProductService service, ModelMapper mapper) {
        super(service, new BaseMapper<>(mapper, Product.class, ProductResponse.class));
    }


    @GetMapping("/")
    @Override
    public ResponseEntity<?> getAllResources(Integer page, HttpServletRequest request) {
        return super.getAllResources(page, request);
    }
}
