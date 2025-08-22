package team.mut4.trip.domain.locationaccomodationbookmark.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.locationaccomodationbookmark.application.LocationAccommodationBookMarkService;
import team.mut4.trip.domain.locationaccomodationbookmark.dto.response.LocationAccommodationBookMarkSaveResponse;

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

}
