package bg.tusofia.fcst.ksi.practikum.fds.controllers.country;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.country.CountryRegionCentreResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre.PopulationCentreJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre.PopulationCentrePagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.country.CountryRegionCentreService;
import bg.tusofia.fcst.ksi.practikum.fds.services.country.CountryRegionService;
import bg.tusofia.fcst.ksi.practikum.fds.services.country.CountryService;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.BaseMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/countries/{countryId}/regions/{regionId}/centres")
public class CountryRegionCentreController
        extends BaseRestController<
            PopulationCentre,
            PopulationCentre,
            PopulationCentre,
            CountryRegionCentreResponse,
            CountryRegionCentreService,
            PopulationCentreJpaRepository,
            PopulationCentrePagingRepository
        > {
    public CountryRegionCentreController(CountryRegionCentreService service, ModelMapper modelMapper, CountryService countryService, CountryRegionService countryRegionService) {
        super(
                service,
                new BaseMapper<>(modelMapper, PopulationCentre.class, CountryRegionCentreResponse.class),
                "/api/v1/resources/countries/{countryId}/regions/{regionId}/centres",
                List.of(countryService, countryRegionService)
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
}
