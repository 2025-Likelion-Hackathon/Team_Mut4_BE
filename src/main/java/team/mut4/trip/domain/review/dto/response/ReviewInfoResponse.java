package team.mut4.trip.domain.review.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.review.domain.Review;

import java.util.List;

@Builder
public record ReviewInfoResponse(

        Long id,

        String username,

        String content

) {
    public static ReviewInfoResponse from(Review review) {
        return new ReviewInfoResponse(
                review.getId(),
                review.getUsername(),
                review.getContent()
        );
    }
}

