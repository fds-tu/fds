package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseResourceEntity;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="fds_user")
public class User extends BaseResourceEntity<Long> {
    @NotBlank(message = "Username cannot be blank")
    @NotBlank(message = "Username cannot be null")
    @Column(unique = true)
    private String username;

    @Email(message = "Email must be a valid email")
    @NotBlank(message = "Email must not be blank")
    @NotNull(message = "Email must not be null")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "First name must not be blank")
    @NotNull(message = "First name must not be null")
    @Size(min = 5, max = 25, message = "First name must have length between 5 and 20 characters")
    @Column(unique = true)
    private String firstName;

    @NotBlank(message = "Second name must not be blank")
    @NotNull(message = "Second name must not be null")
    @Size(min = 5, max = 25, message = "Second name must have length between 5 and 20 characters")
    @Column(unique = true)
    private String secondName;

    @NotBlank(message = "Last name must not be blank")
    @NotNull(message = "Last name must not be null")
    @Size(min = 5, max = 25, message = "Last name must have length between 5 and 20 characters")
    @Column(unique = true)
    private String lastName;

    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    @Size(min = 8, max = 70, message = "Password must have length between 5 and 20 characters")
    private String password;

    @OneToOne(optional = true)
    @JoinColumn(name = "address_id")
    private Address address;
}
