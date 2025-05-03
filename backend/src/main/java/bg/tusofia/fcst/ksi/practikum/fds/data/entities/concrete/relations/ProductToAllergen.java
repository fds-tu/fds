package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_to_allergen", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"primary_id", "secondary_id"})
})
@Data
public class ProductToAllergen extends ManyToMany<Product, Allergen> {
    public static ProductToAllergen generate(Product product, Allergen allergen) {
        ProductToAllergen productToAllergen = new ProductToAllergen();
        productToAllergen.setPrimary(product);
        productToAllergen.setSecondary(allergen);

        return productToAllergen;
    }
}
