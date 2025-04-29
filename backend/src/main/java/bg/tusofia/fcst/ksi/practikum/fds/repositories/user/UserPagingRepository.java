package bg.tusofia.fcst.ksi.practikum.fds.repositories.user;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingRepository extends PagingAndSortingRepository<User, Long> {
}
