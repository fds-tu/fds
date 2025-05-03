package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseResourceEntity;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.ProductToAllergen;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.ProductToCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseResourceEntity<Long> {
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 4, max = 15, message = "Product name must have length between 4 and 15 characters")
    private String name;

    @NotNull(message = "Product weight cannot be null")
    @Min(value = 50, message = "Product weight must be at least 50 grams")
    @Max(value = 2000, message = "Product weight must be at most 2 kg")
    private Integer weight;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "primary")
    private List<ProductToAllergen> productToAllergens = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "primary")
    private List<ProductToCategory> productToCategories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public void addAllergen(ProductToAllergen productToAllergen) {
        productToAllergens.add(productToAllergen);
    }

    public void removeAllergen(ProductToAllergen productToAllergen) {
        productToAllergens.removeIf(p -> p.getSecondary().getId().equals(productToAllergen.getSecondary().getId()));
    }

    public Product removeAllAllergen() {
        productToAllergens.clear();
        return this;
    }

    public  Product removeAllCategories() {
        productToCategories.clear();

        return this;
    }

    public void addCategory(ProductToCategory productToCategory) {
        productToCategories.add(productToCategory);
    }

    public void removeCategory(ProductToCategory productToCategory) {
        productToCategories.removeIf(p -> p.getSecondary().getId().equals(productToCategory.getSecondary().getId()));
    }

    public void addCategories(List<ProductToCategory> productToCategories) {
        this.productToCategories.addAll(productToCategories);
    }

    public void addAllergens(List<ProductToAllergen> productToAllergens) {
        this.productToAllergens.addAll(productToAllergens);
    }
}
