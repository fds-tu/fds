package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.authentication;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class RefreshToken extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;
}
