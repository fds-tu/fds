package bg.tusofia.fcst.ksi.practikum.fds.services.category;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.category.CategoryServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.category.CategoryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.category.CategoryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Long, CategoryJpaRepository, CategoryPagingRepository> {
    public CategoryService(CategoryJpaRepository jpaRepository, CategoryPagingRepository pagingRepository, CategoryServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Category");
    }
}
