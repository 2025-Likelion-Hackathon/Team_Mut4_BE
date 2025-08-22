package team.mut4.trip.domain.locationfoodbookmark.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.locationfoodbookmark.domain.LocationFoodBookMark;
import team.mut4.trip.domain.locationfoodbookmark.domain.LocationFoodBookMarkRepository;
import team.mut4.trip.domain.location.domain.Location;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class LocationFoodBookMarkRepositoryImpl implements LocationFoodBookMarkRepository {

    private final LocationFoodBookMarkJpaRepository locationFoodBookMarkJpaRepository;

    @Override
    public void save(LocationFoodBookMark locationFoodBookMark) {
        locationFoodBookMarkJpaRepository.save(locationFoodBookMark);
    }

    @Override
    public LocationFoodBookMark findByLocationAndFood(Location location, Food food) {
        return locationFoodBookMarkJpaRepository.findByLocationAndFood(location, food);
    }

    @Override
    public List<LocationFoodBookMark> findAllByLocation(Location location) {
        return locationFoodBookMarkJpaRepository.findAllByLocation(location);
    }

}
