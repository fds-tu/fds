package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantResponse {
    private Long id;
    private String name;
    private List<RoleDto> roles;
}
