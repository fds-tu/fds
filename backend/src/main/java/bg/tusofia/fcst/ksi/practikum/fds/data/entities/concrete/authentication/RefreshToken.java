package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RefreshToken extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @NotNull
    private Boolean isUsed = Boolean.FALSE;
}
