package team.mut4.trip.domain.accommodation.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.accommodation.application.AccommodationService;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationDetailResponse;

@RequiredArgsConstructor
@RequestMapping("/accommodations")
@RestController
public class AccommodationController implements AccommodationDocsController{

    private final AccommodationService accommodationService;

    @GetMapping("/{accommodationId}")
    public ResponseEntity<AccommodationDetailResponse> getAccommodation(@PathVariable Long accommodationId) {
        AccommodationDetailResponse response = accommodationService.getAccommodationInfoWithReviews(accommodationId);
        return ResponseEntity.ok(response);
    }

}
