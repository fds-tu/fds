package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseAddressable;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.DeliveryToProduct;
import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Delivery extends BaseAddressable<Long> {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval =  true, mappedBy = "primary")
    private List<DeliveryToProduct> product;

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
