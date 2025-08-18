package team.mut4.trip.domain.location.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.location.application.LocationService;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RequestMapping("/locations")
@RestController
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationSaveResponse> saveLocation(
            @RequestBody LocationSaveRequest request
    ) {
        LocationSaveResponse response = locationService.saveLocation(request);

        return new ResponseEntity<>(response, CREATED);
    }

    @GetMapping("/{locationId}/nearby-food")
    public ResponseEntity<SearchResponse> getNearbyFoodPlaces(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        SearchResponse response = locationService.findNearbyFoodPlaces(locationId, radius);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{locationId}/nearby-accommodation")
    public ResponseEntity<SearchResponse> findNearbyAccommodation(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        SearchResponse response = locationService.findNearbyAccommodations(locationId, radius);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{locationId}/search/food")
    public ResponseEntity<SearchResponse> searchFoodByKeyword(
            @PathVariable Long locationId,
            @RequestParam(defaultValue = "맛집") String keyword,
            @RequestParam(defaultValue = "2000") int radius
    ) {
        return ResponseEntity.ok(locationService.findFoodByKeyword(locationId, keyword, radius));
    }

}
