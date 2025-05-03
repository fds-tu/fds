package bg.tusofia.fcst.ksi.practikum.fds.repositories.population_centre;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.PopulationCentre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PopulationCentreJpaRepository extends JpaRepository<PopulationCentre, Long> {
    Optional<PopulationCentre> findFirstByRegion_IdAndId(Long regionId, Long id);
}
