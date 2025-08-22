package team.mut4.trip.domain.locationaccomodationbookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.locationaccomodationbookmark.application.LocationAccommodationBookMarkService;
import team.mut4.trip.domain.locationaccomodationbookmark.dto.response.LocationAccommodationBookMarkSaveResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/location-accommodation-bookmarks")
@RestController
public class LocationAccommodationBookMarkController {

    private final LocationAccommodationBookMarkService locationAccommodationBookMarkService;

    @PostMapping
    public ResponseEntity<LocationAccommodationBookMarkSaveResponse> saveLocationAccommodationBookMark(
            @RequestParam Long locationId,
            @RequestParam Long accommodationId
    ) {
        LocationAccommodationBookMarkSaveResponse response = locationAccommodationBookMarkService.saveAccommodationBookMark(locationId, accommodationId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<List<AccommodationBasicResponse>> getLocationAccommodationBookMarks(@PathVariable Long locationId) {
        List<AccommodationBasicResponse> responses = locationAccommodationBookMarkService.getLocationAccommodationBookMarks(locationId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}
