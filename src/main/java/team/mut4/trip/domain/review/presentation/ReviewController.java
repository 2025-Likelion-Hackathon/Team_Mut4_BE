package team.mut4.trip.domain.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.review.application.ReviewService;
import team.mut4.trip.domain.review.dto.request.ReviewSaveRequest;
import team.mut4.trip.domain.review.dto.response.ReviewInfoResponse;
import team.mut4.trip.domain.review.dto.response.ReviewSaveResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewController implements ReviewDocsController {

    private final ReviewService reviewService;

    @PostMapping("/{foodId}")
    public ResponseEntity<ReviewSaveResponse> saveReview(
            @PathVariable Long foodId,
            @RequestBody ReviewSaveRequest request
    ) {
        ReviewSaveResponse response = reviewService.saveReview(foodId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
