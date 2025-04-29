package bg.tusofia.fcst.ksi.practikum.fds.repositories.role;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePagingRepository extends JpaRepository<Role, Long> {
}
