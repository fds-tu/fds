package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Session extends BaseEntity<Long> {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
    private List<RefreshToken> refreshTokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotBlank
    @Size(min=7, max=15)
    private String ipv4Address;
}
