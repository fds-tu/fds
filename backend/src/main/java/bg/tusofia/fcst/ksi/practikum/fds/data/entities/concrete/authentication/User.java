package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseResourceEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="fds_user")
@Data
public class User extends BaseResourceEntity<Long> implements UserDetails
{
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
    private String firstName;

    @NotBlank(message = "Second name must not be blank")
    @NotNull(message = "Second name must not be null")
    @Size(min = 5, max = 25, message = "Second name must have length between 5 and 20 characters")
    private String secondName;

    @NotBlank(message = "Last name must not be blank")
    @NotNull(message = "Last name must not be null")
    @Size(min = 5, max = 25, message = "Last name must have length between 5 and 20 characters")
    private String lastName;

    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    @Size(min = 8, max = 70, message = "Password must have length between 8 and 70 characters")
    private String password;

    @OneToOne(optional = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void encodePassword(Function<String, String> encoder) {
        this.setPassword(encoder.apply(this.password));
    }
}
