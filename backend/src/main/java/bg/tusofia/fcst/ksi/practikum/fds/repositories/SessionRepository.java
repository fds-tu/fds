package bg.tusofia.fcst.ksi.practikum.fds.repositories;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.Session;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    <ID> Optional<Session> findByUserIdAndIpv4Address(ID user_id, @NotNull @NotBlank @Size(min = 7, max = 15) String ipv4Address);
}
