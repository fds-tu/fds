package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants;

import bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.category.CategoryResponse;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantProductResponse {
    private Long id;
    private String name;
    private Integer weight;
    private List<AllergenDto> allergens;
    private List<CategoryResponse> categories;
}
