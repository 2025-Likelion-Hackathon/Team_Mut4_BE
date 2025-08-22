package team.mut4.trip.domain.locationfoodbookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.locationfoodbookmark.application.LocationFoodBookMarkService;
import team.mut4.trip.domain.locationfoodbookmark.dto.response.FoodBookMarkSaveResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("location-food-bookmarks")
@RestController
public class LocationFoodBookMarkController {

    private final LocationFoodBookMarkService locationFoodBookMarkService;

    @PostMapping
    public ResponseEntity<FoodBookMarkSaveResponse> saveFoodBookMark(
            @RequestParam Long locationId,
            @RequestParam Long foodId
    ) {
        FoodBookMarkSaveResponse response = locationFoodBookMarkService.saveFoodBookMark(locationId, foodId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<List<FoodBasicResponse>> getLocationFoodBookMarks(@PathVariable Long locationId) {
        List<FoodBasicResponse> bookmarks = locationFoodBookMarkService.getLocationFoodBookMarks(locationId);
        return ResponseEntity.ok(bookmarks);
    }

}
