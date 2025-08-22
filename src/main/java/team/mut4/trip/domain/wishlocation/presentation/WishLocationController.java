package team.mut4.trip.domain.wishlocation.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;
import team.mut4.trip.domain.wishlocation.application.WishLocationService;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/wish-locations")
@RestController
public class WishLocationController implements WishLocationDocsController {

    private final WishLocationService wishLocationService;

    @PostMapping
    public ResponseEntity<WishLocationSaveResponse> saveWishLocation(
            @RequestBody WishLocationSaveRequest request
    ) {
        WishLocationSaveResponse response = wishLocationService.saveWishLocation(request);

        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping("/{wishLocationId}/nearby-food")
    public ResponseEntity<List<FoodBasicResponse>> getNearbyFoodPlaces(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = wishLocationService.findNearbyFoodPlacesAndSaveFood(wishLocationId, radius);
        return new ResponseEntity<>(savedFoods, HttpStatus.OK);
    }

    @GetMapping("/{wishLocationId}/nearby-food-all")
    public ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodPlacesAndSaveFood(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = wishLocationService.findAndSaveAllNearbyFoodPlaces(wishLocationId, radius);
        return ResponseEntity.ok(savedFoods);
    }

    @GetMapping("/{wishLocationId}/search/food")
    public ResponseEntity<List<FoodBasicResponse>> searchAndSaveFood(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "맛집") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<FoodBasicResponse> savedFoods = wishLocationService.searchAndSaveFood(wishLocationId, keyword, radius);
        return ResponseEntity.ok(savedFoods);
    }

    @GetMapping("/{wishLocationId}/nearby-food-all/grade")
    public ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodsSortedByGrade(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(
                wishLocationService.findAndSaveAllNearbyFoodPlacesSortedByGrade(wishLocationId, radius)
        );
    }

    @GetMapping("/{wishLocationId}/search/food/grade")
    public ResponseEntity<List<FoodBasicResponse>> searchAndSaveFoodSortedByGrade(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "맛집") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(
                wishLocationService.searchAndSaveFoodSortedByGrade(wishLocationId, keyword, radius)
        );
    }

    @GetMapping("/{wishLocationId}/nearby-accommodation")
    public ResponseEntity<List<AccommodationBasicResponse>> getNearbyAccommodations(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<AccommodationBasicResponse> savedAccommodations = wishLocationService.findNearbyAccommodationsAndSave(wishLocationId, radius);
        return ResponseEntity.ok(savedAccommodations);
    }

    @GetMapping("/{wishLocationId}/nearby-accommodation-all")
    public ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodations(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<AccommodationBasicResponse> savedAccommodations = wishLocationService.findAndSaveAllNearbyAccommodations(wishLocationId, radius);
        return ResponseEntity.ok(savedAccommodations);
    }

    @GetMapping("/{wishLocationId}/search/accommodation")
    public ResponseEntity<List<AccommodationBasicResponse>> searchAndSaveAccommodations(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "호텔") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        List<AccommodationBasicResponse> savedAccommodations = wishLocationService.searchAndSaveAccommodations(wishLocationId, keyword, radius);
        return ResponseEntity.ok(savedAccommodations);
    }

    @GetMapping("/{wishLocationId}/search/accommodation/grade")
    public ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodationsSortedByGrade(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(
                wishLocationService.findAndSaveAllNearbyAccommodationsSortedByGrade(wishLocationId, radius)
        );
    }

    @GetMapping("/{wishLocationId}/nearby-accommodation-all/grade")
    public ResponseEntity<List<AccommodationBasicResponse>> searchAndSaveAccommodationsSortedByGrade(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "호텔") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(
                wishLocationService.searchAndSaveAccommodationsSortedByGrade(wishLocationId, keyword, radius)
        );
    }

}
