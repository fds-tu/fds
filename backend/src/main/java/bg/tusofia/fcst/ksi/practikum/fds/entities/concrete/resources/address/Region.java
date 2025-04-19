package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Region extends BaseEntity<Long> {
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "country_id", unique = true, nullable = false)
    private Country country;

    @NotNull(message = "Region name cannot be null")
    @NotBlank(message = "Region name cannot be blank")
    @Size(min = 3, max = 25, message = "Region name must have length between 3 and 25 characters")
    private String name;
}
