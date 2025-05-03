package bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.relations;

import bg.tusofia.fcst.ksi.practikum.fds.data.entities.base.relations.ManyToMany;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Category;
import bg.tusofia.fcst.ksi.practikum.fds.data.entities.concrete.resources.Restaurant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurant_to_category", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"primary_id", "secondary_id"})
})
@Data
public class RestaurantToCategory extends ManyToMany<Restaurant, Category> {
    public static RestaurantToCategory generate(Restaurant restaurant, Category category) {
        RestaurantToCategory restaurantToCategory = new RestaurantToCategory();
        restaurantToCategory.setPrimary(restaurant);
        restaurantToCategory.setSecondary(category);

        return restaurantToCategory;
    }
}


