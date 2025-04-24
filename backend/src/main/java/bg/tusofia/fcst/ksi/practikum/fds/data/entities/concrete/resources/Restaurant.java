package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseAddressable;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
public class Restaurant extends BaseAddressable<Long> {
    @NotNull(message = "Restaurant name cannot be null")
    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(min = 4, max = 15, message = "Restaurant name must have length between 4 and 15 characters")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_to_restaurant",
            joinColumns = @JoinColumn(name = "right_id"),
            inverseJoinColumns = @JoinColumn(name = "left_id")
    )
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
