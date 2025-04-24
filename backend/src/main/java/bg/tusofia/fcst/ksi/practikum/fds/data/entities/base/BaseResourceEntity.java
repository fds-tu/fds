package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseResourceEntity<T> extends BaseEntity<T> {
    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp lastModifiedDate;
}
