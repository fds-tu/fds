package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.users.UserDto;
import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
    private Long id;
    private UserDto user;
    private RestaurantAccessType restaurantAccessType;

    public RoleDto(UserDto user, @NotNull RestaurantAccessType restaurantAccessType) {
        this.user = user;
        this.restaurantAccessType = restaurantAccessType;
    }
}
