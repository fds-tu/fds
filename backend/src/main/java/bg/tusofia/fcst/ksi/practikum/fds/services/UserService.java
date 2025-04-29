package bg.tusofia.fcst.ksi.practikum.fds.services;

import bg.tusofia.fcst.ksi.practikum.fds.authorizers.UserServiceAuthorizer;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.user.UserJpaRepository;
import bg.tusofia.fcst.ksi.practikum.fds.repositories.user.UserPagingRepository;
import bg.tusofia.fcst.ksi.practikum.fds.services.base.BaseService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserJpaRepository, UserPagingRepository> {
    public UserService(UserJpaRepository jpaRepository, UserPagingRepository pagingRepository, UserServiceAuthorizer authorizer) {
        super(jpaRepository, pagingRepository, authorizer, "User");
    }
}
