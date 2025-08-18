package team.mut4.trip.domain.food.domain;

import java.util.Optional;

public interface FoodRepository {

    Food save(Food food);

    Optional<Food> findByNameAndAddress(String name, String address);

    Food findByFoodId(Long foodId);

}
