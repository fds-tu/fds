package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.users;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private String username;
}
