package team.mut4.trip.domain.foodreview.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.foodreview.dto.request.FoodReviewSaveRequest;
import team.mut4.trip.domain.foodreview.dto.response.FoodReviewSaveResponse;

@Tag(name = "Review", description = "음식점 리뷰 관련 API")
@RequestMapping("/reviews")
public interface ReviewDocsController {

    @Operation(summary = "음식점 리뷰 저장", description = "음식점에 대한 리뷰를 저장합니다.")
    @PostMapping("/{foodId}")
    ResponseEntity<FoodReviewSaveResponse> saveReview(
            @Parameter(description = "리뷰를 작성할 음식점 ID") @PathVariable Long foodId,
            @Parameter(description = "리뷰 저장 요청") @RequestBody FoodReviewSaveRequest request
    );

}
