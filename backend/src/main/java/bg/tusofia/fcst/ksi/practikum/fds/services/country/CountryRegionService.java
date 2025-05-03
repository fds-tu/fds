package bg.tusofia.fcst.ksi.practikum.fds.services.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.country.CountryRegionServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.region.RegionJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.region.RegionPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryRegionService extends BaseService<Region, Long, RegionJpaRepository, RegionPagingRepository> {
    public CountryRegionService(RegionJpaRepository jpaRepository, RegionPagingRepository pagingRepository, CountryRegionServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Region");
    }

    @Override
    protected Region getResourceInternal(Long resourceId, List<Object> parentResources) {
        Country country = (Country) parentResources.getFirst();
        return this.jpaRepository.findFirstByCountry_IdAndId(country.getId(), resourceId).orElseThrow(() -> new ResourceNotFoundException(this.resourceName, "Id", resourceId.toString()));
    }

    @Override
    protected List<Region> getResourcesInternal(int page, int size, String sortBy, List<Object> parentResources) {
        Country country = (Country) parentResources.getFirst();
        return this.pagingRepository.findAllByCountry_Id(country.getId(), PageRequest.of(page, size, Sort.by("createdDate")));
    }
}
