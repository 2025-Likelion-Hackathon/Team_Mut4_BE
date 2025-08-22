package team.mut4.trip.domain.locationfoodbookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.locationfoodbookmark.domain.LocationFoodBookMark;
import team.mut4.trip.domain.location.domain.Location;

import java.util.List;

public interface LocationFoodBookMarkJpaRepository extends JpaRepository<LocationFoodBookMark, Long> {

    LocationFoodBookMark findByLocationAndFood(Location location, Food food);

    List<LocationFoodBookMark> findAllByLocation(Location location);

}
