package team.mut4.trip.domain.accommodationreviewtag.dto.response;

import lombok.Builder;

@Builder
public record AccommodationReviewTagSummaryResponse(

        Long id,

        String tagName,

        Long count

) {
}
