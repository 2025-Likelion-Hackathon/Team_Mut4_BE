package team.mut4.trip.domain.wishlocation.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;
import team.mut4.trip.domain.wishlocation.application.WishLocationService;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/wish-locations")
@RestController
public class WishLocationController {

    private final WishLocationService wishLocationService;

    @PostMapping
    public ResponseEntity<WishLocationSaveResponse> saveWishLocation(
            @RequestBody WishLocationSaveRequest request
    ) {
        WishLocationSaveResponse response = wishLocationService.saveWishLocation(request);

        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping("/{wishLocationId}/nearby-food")
    public ResponseEntity<SearchResponse> getNearbyFoodPlaces(
            @PathVariable Long wishLocationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        SearchResponse response = wishLocationService.findNearbyFoodPlaces(wishLocationId, radius);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
