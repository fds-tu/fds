package bg.tusofia.fcst.ksi.practikum.fds.repositories;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
