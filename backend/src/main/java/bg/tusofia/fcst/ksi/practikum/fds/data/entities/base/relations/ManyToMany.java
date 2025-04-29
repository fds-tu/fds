package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class ManyToMany<T1, T2> extends BaseEntity<Long> {
    @ManyToOne(optional = false)
    @JoinColumn(name = "primary_id")
    protected T1 primary;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "secondary_id")
    protected T2 secondary;
}
