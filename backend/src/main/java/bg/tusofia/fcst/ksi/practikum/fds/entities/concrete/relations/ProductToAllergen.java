package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Allergen;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Product;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Restaurant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "product_to_allergen", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"left_id", "right_id"})
})
public class ProductToAllergen extends ManyToMany<Product, Allergen> {
}
