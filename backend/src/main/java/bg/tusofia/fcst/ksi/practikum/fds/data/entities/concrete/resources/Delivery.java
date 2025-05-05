package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.BaseAddressable;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.authentication.User;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations.DeliveryToProduct;
import bg.tusofia.fcst.ksi.practikum.fds.enums.delivery.DeliveryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Delivery extends BaseAddressable<Long> {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval =  true, mappedBy = "primary")
    private List<DeliveryToProduct> products;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private User courier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
