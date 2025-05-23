package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private ID id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = Timestamp.from(Instant.now());
        }
    }
}