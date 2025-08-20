package team.mut4.trip.domain.accommodationreview.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AccommodationReviewSaveRequest(

        @NotBlank(message = "숙소 리뷰 내용을 입력해 주세요.")
        String content

) {
}
