package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseAddressable;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Restaurant extends BaseAddressable<Long> {
    @NotNull(message = "Restaurant name cannot be null")
    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(min = 4, max = 15, message = "Restaurant name must have length between 4 and 15 characters")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "secondary")
    private List<Role> roles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Product> products;

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.removeIf(r -> r.getPrimary().getId().equals(role.getPrimary().getId()));
    }

    public void addProduct(Product product) {products.add(product);}

    public void removeProduct(Product product) {products.removeIf(p -> p.getId().equals(product.getId()));}
}
