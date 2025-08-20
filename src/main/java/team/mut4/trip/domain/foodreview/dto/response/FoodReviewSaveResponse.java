package team.mut4.trip.domain.foodreview.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "음식점 리뷰 저장 응답 DTO")
@Builder
public record FoodReviewSaveResponse(

        @Schema(description = "저장된 리뷰 ID", example = "1")
        Long reviewId

) {
}
