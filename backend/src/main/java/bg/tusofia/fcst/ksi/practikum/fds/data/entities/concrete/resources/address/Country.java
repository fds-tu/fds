package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends BaseEntity<Long> {
    @NotBlank(message = "Country code cannot be blank")
    @NotNull(message = "Country code cannot be null")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    @Column(unique = true)
    private String code;

    @NotBlank(message = "Country name cannot be blank")
    @NotNull(message = "Country name cannot be blank")
    @Size(min = 2, max = 20, message = "Country name must be at least 2 and at most 20 characters")
    private String name;
}
