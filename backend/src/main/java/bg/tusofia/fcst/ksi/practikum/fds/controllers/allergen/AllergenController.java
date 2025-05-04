package bg.tusofia.fcst.ksi.practikum.fds.controllers.allergen;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseGetController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants.AllergenDto;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen.AllergenJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.allergen.AllergenPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.allergen.AllergenService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resources/allergens")
public class AllergenController
        extends BaseGetController<
            Allergen,
            AllergenDto,
            AllergenService,
            AllergenJpaRepository,
            AllergenPagingRepository
        > {
    public AllergenController(AllergenService service, ModelMapper mapper) {
        super(service, mapper, Allergen.class, AllergenDto.class);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<?> getAllResources(Integer page, HttpServletRequest request) {
        return super.getAllResources(page, request);
    }
}
