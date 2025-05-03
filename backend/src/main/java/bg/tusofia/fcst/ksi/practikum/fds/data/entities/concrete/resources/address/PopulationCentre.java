package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "population_centre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopulationCentre extends BaseEntity<Long> {
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @NotNull(message = "Population centre name cannot be null")
    @NotBlank(message = "Population centre name cannot be blank")
    @Size(min = 3, max = 25, message = "Population centre name must have length between 3 and 25 characters")
    private String name;
}
