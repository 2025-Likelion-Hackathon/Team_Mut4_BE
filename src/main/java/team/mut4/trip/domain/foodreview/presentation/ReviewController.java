package team.mut4.trip.domain.foodreview.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.foodreview.application.FoodReviewService;
import team.mut4.trip.domain.foodreview.dto.request.FoodReviewSaveRequest;
import team.mut4.trip.domain.foodreview.dto.response.FoodReviewSaveResponse;

@RequiredArgsConstructor
@RequestMapping("/food-reviews")
@RestController
public class ReviewController implements ReviewDocsController {

    private final FoodReviewService foodReviewService;

    @PostMapping("/{foodId}")
    public ResponseEntity<FoodReviewSaveResponse> saveReview(
            @PathVariable Long foodId,
            @RequestBody FoodReviewSaveRequest request
    ) {
        FoodReviewSaveResponse response = foodReviewService.saveReview(foodId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
