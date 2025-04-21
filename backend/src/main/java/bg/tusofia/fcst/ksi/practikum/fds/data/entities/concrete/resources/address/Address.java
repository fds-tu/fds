package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Address extends BaseEntity<Long> {
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "population_centre_id", unique = true, nullable = false)
    private PopulationCentre populationCentre;

    @NotBlank(message = "Address line 1 cannot be blank")
    @NotNull(message = "Address line 1 cannot be null")
    private String addressLine1;

    @NotBlank(message = "Address line 2 cannot be blank")
    @NotNull(message = "Address line 2 cannot be null")
    private String addressLine2;

    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;
}
