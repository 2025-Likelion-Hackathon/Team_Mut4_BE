package team.mut4.trip.domain.accommodationreview.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;

@Builder
public record AccommodationReviewInfoResponse(

        Long id,

        String username,

        String content

) {
    public static AccommodationReviewInfoResponse from(AccommodationReview accommodationReview) {
        return new AccommodationReviewInfoResponse(
                accommodationReview.getId(),
                accommodationReview.getUsername(),
                accommodationReview.getContent()
        );
    }
}
