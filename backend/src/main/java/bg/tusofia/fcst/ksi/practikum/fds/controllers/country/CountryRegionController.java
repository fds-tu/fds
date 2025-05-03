package bg.tusofia.fcst.ksi.practikum.fds.controllers.country;

import bg.tusofia.fcst.ksi.practikum.fds.controllers.base.BaseRestController;
import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.country.CountryRegionResponse;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.region.RegionJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.region.RegionPagingRepository;
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
@RequestMapping("/api/v1/resources/countries/{countryId}/regions")
public class CountryRegionController
        extends BaseRestController<
            Region,
            Region,
            Region,
            CountryRegionResponse,
            CountryRegionService,
            RegionJpaRepository,
            RegionPagingRepository
        >

{
    public CountryRegionController(CountryRegionService service, ModelMapper modelMapper, CountryService countryService) {
        super(service, new BaseMapper<>(modelMapper, Region.class, CountryRegionResponse.class), "/api/v1/resources/countries/{countryId}/regions", List.of(countryService));
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
