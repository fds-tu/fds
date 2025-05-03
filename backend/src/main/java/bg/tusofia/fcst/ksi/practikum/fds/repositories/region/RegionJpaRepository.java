package bg.tusofia.fcst.ksi.practikum.fds.repositories.region;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionJpaRepository extends JpaRepository<Region, Long> {
    Optional<Region> findFirstByCountry_IdAndId(Long countryId, Long id);
}
