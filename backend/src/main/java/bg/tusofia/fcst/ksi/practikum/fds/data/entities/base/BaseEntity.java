package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private ID id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public ID getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}