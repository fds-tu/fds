package bg.tusofia.fcst.ksi.practikum.fds.data.dtos.responses.restaurants;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Integer weight;
    private List<AllergenDto> allergens;
}
