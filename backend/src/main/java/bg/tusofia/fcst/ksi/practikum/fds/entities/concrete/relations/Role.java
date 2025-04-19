package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.AccessType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_to_restaurant", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"left_id", "right_id"})
})
public class Role extends ManyToMany<User, Restaurant> {
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccessType accessType;

}
