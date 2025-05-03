package bg.tusofia.fcst.ksi.practikum.fds.repositories.region;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RegionPagingRepository extends PagingAndSortingRepository<Region, Long> {
    List<Region> findAllByCountry_Id(Long countryId, Pageable pageable);
}
