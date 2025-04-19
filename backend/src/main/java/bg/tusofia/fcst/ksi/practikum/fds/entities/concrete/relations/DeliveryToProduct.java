package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Delivery;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "delivery_to_product", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"left_id", "right_id"})
})
public class DeliveryToProduct extends ManyToMany<Delivery, Product> {
}
