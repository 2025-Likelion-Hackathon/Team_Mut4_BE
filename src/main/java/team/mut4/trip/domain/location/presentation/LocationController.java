package team.mut4.trip.domain.location.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.application.LocationService;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/locations")
@RestController
public class LocationController implements LocationDocsController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationSaveResponse> saveLocation(
            @RequestBody LocationSaveRequest request
    ) {
        LocationSaveResponse response = locationService.saveLocation(request);

        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping("/{locationId}/nearby-food")
    public ResponseEntity<List<FoodBasicResponse>> getNearbyFoodPlacesAndSaveFood(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = locationService.findNearbyFoodPlacesAndSaveFood(locationId, radius);
        return ResponseEntity.ok(savedFoods);
    }

    @GetMapping("/{locationId}/nearby-food-all")
    public ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodPlacesAndSaveFood(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = locationService.findAndSaveAllNearbyFoodPlaces(locationId, radius);
        return ResponseEntity.ok(savedFoods);
    }

    @GetMapping("/{locationId}/nearby-accommodation")
    public ResponseEntity<SearchResponse> findNearbyAccommodation(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        SearchResponse response = locationService.findNearbyAccommodations(locationId, radius);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{locationId}/search/food/save")
    public ResponseEntity<List<FoodBasicResponse>> searchAndSaveFood(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "맛집") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = locationService.searchAndSaveFood(locationId, keyword, radius);
        return ResponseEntity.ok(savedFoods);
    }


    @GetMapping("/{locationId}/search/accommodation")
    public ResponseEntity<SearchResponse> searchByKeyword(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "호텔") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(locationService.findAccommodationsByKeyword(locationId, keyword, radius));
    }

}
