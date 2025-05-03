package bg.tusofia.fcst.ksi.practikum.fds.controllers.country;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.country.CountryResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.country.CountryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.country.CountryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.country.CountryService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resources/countries")
public class CountryController extends BaseRestController<Country, Country, Country, CountryResponse, CountryService, CountryJpaRepository, CountryPagingRepository> {
    public CountryController(CountryService service, ModelMapper modelMapper) {
        super(service, new BaseMapper<>(modelMapper, Country.class, CountryResponse.class));
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
