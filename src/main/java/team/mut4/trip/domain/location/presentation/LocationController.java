package team.mut4.trip.domain.location.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.location.application.LocationService;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;

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

}
