package bg.tusofia.fcst.ksi.practikum.fds.services.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.country.CountryRegionCentreServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.ResourceAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.InvalidResourceAccessTypeException;
import bg.tusofia.fcst.ksi.practikum.fds.exceptions.rest.ResourceNotFoundException;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre.PopulationCentreJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre.PopulationCentrePagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryRegionCentreService extends BaseService<PopulationCentre, Long, PopulationCentreJpaRepository, PopulationCentrePagingRepository> {
    public CountryRegionCentreService(PopulationCentreJpaRepository jpaRepository, PopulationCentrePagingRepository pagingRepository, CountryRegionCentreServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Population Centre");
    }

    @Override
    protected PopulationCentre getResourceInternal(Long resourceId, List<Object> parentResources) {
        Country country = (Country) parentResources.getFirst();
        Region region = (Region) parentResources.get(1);
        if(!region.getCountry().getId().equals(country.getId())) {
            throw new InvalidResourceAccessTypeException(ResourceAccessType.GET_SPECIFIC);
        }

        return this.jpaRepository.findFirstByRegion_IdAndId(region.getId(), resourceId).orElseThrow(() -> new ResourceNotFoundException("Population Centre", "Id", resourceId.toString()));
    }

    @Override
    protected List<PopulationCentre> getResourcesInternal(int page, int size, String sortBy, List<Object> parentResources) {
        Country country = (Country) parentResources.getFirst();
        Region region = (Region) parentResources.get(1);
        if(!region.getCountry().getId().equals(country.getId())) {
            throw new InvalidResourceAccessTypeException(ResourceAccessType.GET_ALL);
        }

        return this.pagingRepository.findAllByRegion_Id(region.getId(), PageRequest.of(page, size, Sort.by("createdDate")));
    }
}
