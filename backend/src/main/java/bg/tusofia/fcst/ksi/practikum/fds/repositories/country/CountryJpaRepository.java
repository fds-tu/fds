package bg.tusofia.fcst.ksi.practikum.fds.repositories.country;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryJpaRepository extends JpaRepository<Country, Long> {
}
