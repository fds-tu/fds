package bg.tusofia.fcst.ksi.practikum.fds.repositories;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}