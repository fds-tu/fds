package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.authentication;

import lombok.Data;

@Data
public class CreateAccessRequest {
    private String username;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String password;
}
