package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.requests.resources.restaurants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class EditRestaurantRequest {
    @NotNull(message = "Restaurant name cannot be null")
    @NotBlank(message = "Restaurant name cannot be blank")
    @Size(min = 4, max = 15, message = "Restaurant name must have length between 4 and 15 characters")
    private String name;
    
    private List<Long> categoryIds;
}
