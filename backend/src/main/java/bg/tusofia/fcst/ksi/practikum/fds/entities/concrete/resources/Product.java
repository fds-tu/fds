package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseResourceEntity;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.relations.ProductToAllergen;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.relations.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Entity
public class Product extends BaseResourceEntity<Long> {
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 4, max = 15, message = "Product name must have length between 4 and 15 characters")
    private String name;

    @NotNull(message = "Product weight cannot be null")
    @Min(value = 50, message = "Product weight must be at least 50 grams")
    @Max(value = 2000, message = "Product weight must be at most 2 kg")
    private Integer weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_to_allergen",
            joinColumns = @JoinColumn(name = "right_id"),
            inverseJoinColumns = @JoinColumn(name = "left_id")
    )
    private Set<ProductToAllergen> productToAllergens;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

}
