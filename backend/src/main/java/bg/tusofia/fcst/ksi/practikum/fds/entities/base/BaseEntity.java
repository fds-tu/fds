package bg.tusofia.fcst.ksi.practikum.fds.entities.base;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private T id;
}