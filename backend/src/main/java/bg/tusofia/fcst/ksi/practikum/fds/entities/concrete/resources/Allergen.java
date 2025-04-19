package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Allergen extends BaseEntity<Long> {
    @NotNull(message = "Allergen name cannot be null")
    @NotBlank(message = "Allergen name cannot be blank")
    @Size(min = 4, max = 15, message = "Allergen name must have length between 4 and 15 characters")
    @Column(unique = true)
    private String name;
}
