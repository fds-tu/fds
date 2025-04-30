package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.products;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateProductRequest {
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 4, max = 15, message = "Product name must have length between 4 and 15 characters")
    private String name;

    @NotNull(message = "Product weight cannot be null")
    @Min(value = 50, message = "Product weight must be at least 50 grams")
    @Max(value = 2000, message = "Product weight must be at most 2 kg")
    private Integer weight;
}
