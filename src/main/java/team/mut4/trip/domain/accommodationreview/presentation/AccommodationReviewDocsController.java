package team.mut4.trip.domain.accommodationreview.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodationreview.dto.request.AccommodationReviewSaveRequest;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewSaveResponse;

@Tag(name = "AccommodationReview", description = "숙소 리뷰 관련 API")
@RequestMapping("/accommodation-reviews")
public interface AccommodationReviewDocsController {

    @Operation(summary = "숙소 리뷰 저장", description = "숙소에 대한 리뷰를 저장합니다.")
    @PostMapping("/{accommodationId}")
    ResponseEntity<AccommodationReviewSaveResponse> saveAccommodationReview(
            @Parameter(description = "리뷰를 작성할 숙소 ID") @PathVariable Long accommodationId,
            @Parameter(description = "리뷰 저장 요청") @RequestBody AccommodationReviewSaveRequest request
    );

}
