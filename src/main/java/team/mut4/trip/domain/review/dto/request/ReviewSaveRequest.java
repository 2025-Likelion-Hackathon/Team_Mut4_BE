package team.mut4.trip.domain.review.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ReviewSaveRequest(

        @NotBlank(message = "리뷰를 입력해 주세요.")
        String content,

        String grade,

        List<Long> foodTagIds

) {
}
