package team.mut4.trip.domain.review.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.review.domain.Review;

@Builder
public record ReviewInfoResponse(

        String username,

        String content

) {
    public static ReviewInfoResponse from(Review review) {
        return ReviewInfoResponse.builder()
                .username(review.getUsername())
                .content(review.getContent())
                .build();
    }
}
