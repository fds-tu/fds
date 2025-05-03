package bg.tusofia.fcst.ksi.practikum.fds.services.product;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.base.AllAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.product.ProductPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, Long, ProductJpaRepository, ProductPagingRepository> {
    public ProductService(ProductJpaRepository jpaRepository, AllAuthorizer<Product> authorizer, ProductPagingRepository pagingRepository) {
        super(jpaRepository, pagingRepository, authorizer, "Product");
    }
}
