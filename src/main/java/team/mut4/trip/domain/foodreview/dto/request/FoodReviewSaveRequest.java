package team.mut4.trip.domain.foodreview.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Schema(description = "음식점 리뷰 저장 요청 DTO")
public record FoodReviewSaveRequest(

        @Schema(description = "음식점 리뷰 내용", example = "음식이 정말 맛있었어요!")
        @NotBlank(message = "리뷰를 입력해 주세요.")
        String content,

        @Schema(description = "음식점 추천 등급 (A, B, C, D, E 중 한 개 선택)", example = "A")
        String grade,

        @Schema(
                description = "리뷰에 연결할 음식 키워드 ID 목록 " +
                        "(디비에 8개 있습니다. 맛잇어요부터 가성비 좋아요 까지 다 있어요)",
                example = "[1, 3, 5]"
        )
        List<Long> foodTagIds

) {
}
