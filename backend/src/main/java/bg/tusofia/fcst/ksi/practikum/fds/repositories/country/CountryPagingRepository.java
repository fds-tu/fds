package bg.tusofia.fcst.ksi.practikum.fds.repositories.country;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryPagingRepository extends PagingAndSortingRepository<Country, Long> {
}
