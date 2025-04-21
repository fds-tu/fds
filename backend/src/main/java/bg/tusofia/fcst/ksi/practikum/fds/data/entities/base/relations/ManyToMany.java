package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class ManyToMany<T1, T2> extends BaseEntity<Long> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "left_id")
    private T1 one;

    @ManyToOne(optional = false)
    @JoinColumn(name = "right_id")
    private T2 two;
}
