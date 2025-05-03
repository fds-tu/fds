package bg.tusofia.fcst.ksi.practikum.fds.services.country;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.country.CountryServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.country.CountryJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.country.CountryPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends BaseService<Country, Long, CountryJpaRepository, CountryPagingRepository> {
    public CountryService(CountryJpaRepository jpaRepository, CountryPagingRepository pagingRepository, CountryServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "Country");
    }
}
