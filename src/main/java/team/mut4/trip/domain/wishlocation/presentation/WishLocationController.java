package team.mut4.trip.domain.wishlocation.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
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

}
