package bg.tusofia.fcst.ksi.practikum.fds.controllers.category;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseGetController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.category.CategoryResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.category.CategoryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.category.CategoryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.category.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resources/categories")
public class CategoryController
        extends BaseGetController<
            Category,
            CategoryResponse,
            CategoryService,
            CategoryJpaRepository,
            CategoryPagingRepository
        > {

    public CategoryController(CategoryService service, ModelMapper modelMapper) {
        super(service, modelMapper, Category.class, CategoryResponse.class);
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
}
