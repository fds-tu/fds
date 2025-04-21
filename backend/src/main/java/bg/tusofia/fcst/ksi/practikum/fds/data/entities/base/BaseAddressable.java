package bg.tusofia.fcst.ksi.practikum.fds.data.entities.base;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.address.Address;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class BaseAddressable<ID> extends BaseResourceEntity<ID> {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
