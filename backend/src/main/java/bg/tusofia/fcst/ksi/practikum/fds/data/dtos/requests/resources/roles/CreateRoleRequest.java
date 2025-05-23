package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.roles;

import bg.tusofia.fcst.ksi.practikum.fds.enums.authorization.RestaurantAccessType;
import bg.tusofia.fcst.ksi.practikum.fds.utilities.validators.enums.EnumValid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoleRequest {
    @NotNull(message = "Username cannot be null")
    private String username;

    @EnumValid(message = "Access type must be a valid value", enumClass = RestaurantAccessType.class)
    private RestaurantAccessType accessType;
}
