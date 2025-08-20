package team.mut4.trip.domain.accommodationreview.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(description = "숙소 리뷰 저장 요청 DTO")
public record AccommodationReviewSaveRequest(

        @Schema(description = "숙소 리뷰 내용", example = "숙소가 정말 깨끗하고 편안했어요!")
        @NotBlank(message = "숙소 리뷰 내용을 입력해 주세요.")
        String content,

        @Schema(description = "숙소 등급 (A, B, C, D, E 중 한 개 선택)", example = "A")
        String grade,

        @Schema(
                description = "리뷰에 연결할 숙소 태그 ID 목록 " +
                        "(예: 편안해요, 위치 좋아요, 친절해요 등)",
                example = "[1, 3, 4]"
        )
        List<Long> accommodationTagIds

) {
}
