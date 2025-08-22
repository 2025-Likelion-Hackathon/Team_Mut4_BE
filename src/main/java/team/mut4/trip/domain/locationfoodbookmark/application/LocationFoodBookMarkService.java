package team.mut4.trip.domain.locationfoodbookmark.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.locationfoodbookmark.domain.LocationFoodBookMark;
import team.mut4.trip.domain.locationfoodbookmark.domain.LocationFoodBookMarkRepository;
import team.mut4.trip.domain.locationfoodbookmark.dto.response.FoodBookMarkSaveResponse;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.domain.LocationRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationFoodBookMarkService {

    private final LocationFoodBookMarkRepository locationFoodBookMarkRepository;
    private final LocationRepository locationRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public FoodBookMarkSaveResponse saveFoodBookMark(Long locationId, Long foodId) {
        Location location = locationRepository.findByLocationId(locationId);
        Food food = foodRepository.findByFoodId(foodId);

        LocationFoodBookMark existing = locationFoodBookMarkRepository.findByLocationAndFood(location, food);
        if (existing != null) {
            return FoodBookMarkSaveResponse.builder()
                    .foodBookMarkId(existing.getId())
                    .build();
        }

        LocationFoodBookMark locationFoodBookMark = LocationFoodBookMark.builder()
                .location(location)
                .food(food)
                .build();
        locationFoodBookMarkRepository.save(locationFoodBookMark);

        return FoodBookMarkSaveResponse.builder()
                .foodBookMarkId(locationFoodBookMark.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<FoodBasicResponse> getLocationFoodBookMarks(Long locationId) {
        Location location = locationRepository.findByLocationId(locationId);

        List<LocationFoodBookMark> bookmarks = locationFoodBookMarkRepository.findAllByLocation(location);

        return bookmarks.stream()
                .map(bookmark -> {
                    Food food = bookmark.getFood();
                    return FoodBasicResponse.from(food, food.getAverageGrade() != null ? food.getAverageGrade().name() : "N/A");
                })
                .toList();
    }

}
