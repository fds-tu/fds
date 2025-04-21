package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Session extends BaseEntity<Long> {
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
    private List<RefreshToken> usedRefreshTokens;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotBlank
    @Size(min=7, max=15)
    private String ipv4Address;

    public void addUsedRefreshToken(RefreshToken token) {
        this.usedRefreshTokens.add(token);
    }
}
