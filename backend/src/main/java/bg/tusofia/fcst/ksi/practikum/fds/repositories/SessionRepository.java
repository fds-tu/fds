package bg.tusofia.fcst.ksi.practikum.fds.repositories;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUserIdAndIpv4Address(Long userId, String ipv4Address);
}
