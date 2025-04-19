package bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.entities.base.BaseAddressable;
import bg.tusofia.fcst.ksi.practikum.fds.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Delivery extends BaseAddressable<Long> {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "delivery_to_product",
        joinColumns = @JoinColumn(name = "left_id"),
        inverseJoinColumns = @JoinColumn(name = "right_id")
    )
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStatus deliveryStatus;
}
