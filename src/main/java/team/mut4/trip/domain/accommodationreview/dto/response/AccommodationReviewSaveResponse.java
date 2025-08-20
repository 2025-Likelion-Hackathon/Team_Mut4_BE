package team.mut4.trip.domain.accommodationreview.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "숙소 리뷰 저장 응답 DTO")
@Builder
public record AccommodationReviewSaveResponse(

        @Schema(description = "저장된 숙소 리뷰 ID", example = "1")
        Long accommodationReviewId

) {
}
