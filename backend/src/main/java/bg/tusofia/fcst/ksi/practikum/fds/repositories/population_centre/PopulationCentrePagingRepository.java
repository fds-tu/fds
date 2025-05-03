package bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PopulationCentrePagingRepository extends PagingAndSortingRepository<PopulationCentre, Long> {
    List<PopulationCentre> findAllByRegion_Id(Long regionId, Pageable pageable);
}
