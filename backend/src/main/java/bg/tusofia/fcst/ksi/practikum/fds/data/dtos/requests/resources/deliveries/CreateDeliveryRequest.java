package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.deliveries;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateDeliveryRequest {
    @NotNull(message = "Product Ids list cannot be null")
    @NotEmpty(message = "Product Ids list cannot be empty")
    private List<Long> productIds;
}
