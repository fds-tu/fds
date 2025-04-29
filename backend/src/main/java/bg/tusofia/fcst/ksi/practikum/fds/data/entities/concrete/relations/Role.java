package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_to_restaurant", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"primary_id", "secondary_id"})
})
@Data
@Getter
@Setter
public class Role extends ManyToMany<User, Restaurant> {
    @Enumerated(EnumType.STRING)
    @NotNull
    private RestaurantAccessType restaurantAccessType;
}
