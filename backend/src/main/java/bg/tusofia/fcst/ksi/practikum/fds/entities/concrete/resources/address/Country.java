package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Country extends BaseEntity<Long> {
    @NotBlank(message = "Country code cannot be blank")
    @NotNull(message = "Country code cannot be null")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Country name cannot be blank")
    @NotNull(message = "Country name cannot be blank")
    @Size(min = 2, max = 2, message = "Country name must be exactly 2 characters")
    private String name;
}
