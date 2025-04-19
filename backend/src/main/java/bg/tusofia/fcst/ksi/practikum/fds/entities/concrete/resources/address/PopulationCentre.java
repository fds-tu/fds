package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "population_centre")
public class PopulationCentre extends BaseEntity<Long> {
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "region_id", unique = true, nullable = false)
    private Region region;

    @NotNull(message = "Population centre name cannot be null")
    @NotBlank(message = "Population centre name cannot be blank")
    @Size(min = 3, max = 25, message = "Population centre name must have length between 3 and 25 characters")
    private String name;

}
