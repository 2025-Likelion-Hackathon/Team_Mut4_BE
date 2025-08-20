package team.mut4.trip.domain.accommodationreview.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodationreview.application.AccommodationReviewService;
import team.mut4.trip.domain.accommodationreview.dto.request.AccommodationReviewSaveRequest;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewSaveResponse;

@RequiredArgsConstructor
@RequestMapping("/accommodation-reviews")
@RestController
public class AccommodationReviewController implements AccommodationReviewDocsController{

    private final AccommodationReviewService accommodationReviewService;

    @PostMapping("/{accommodationId}")
    public ResponseEntity<AccommodationReviewSaveResponse> saveAccommodationReview(
            @PathVariable Long accommodationId,
            @RequestBody AccommodationReviewSaveRequest request
    ) {
        AccommodationReviewSaveResponse response = accommodationReviewService.saveAccommodationReview(accommodationId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
