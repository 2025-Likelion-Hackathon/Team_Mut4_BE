package team.mut4.trip.domain.locationfoodbookmark.domain;

import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.location.domain.Location;

public interface LocationFoodBookMarkRepository {

    void save(LocationFoodBookMark locationFoodBookMark);

    LocationFoodBookMark findByLocationAndFood(Location location, Food food);

}
