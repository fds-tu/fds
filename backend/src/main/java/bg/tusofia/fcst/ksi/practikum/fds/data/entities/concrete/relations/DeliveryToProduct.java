package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Delivery;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "delivery_to_product", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"primary_id", "secondary_id"})
})
public class DeliveryToProduct extends ManyToMany<Delivery, Product> {
    public static DeliveryToProduct generate(Delivery delivery, Product product) {
        DeliveryToProduct deliveryToProduct = new DeliveryToProduct();
        deliveryToProduct.setPrimary(delivery);
        deliveryToProduct.setSecondary(product);

        return deliveryToProduct;
    }
}
