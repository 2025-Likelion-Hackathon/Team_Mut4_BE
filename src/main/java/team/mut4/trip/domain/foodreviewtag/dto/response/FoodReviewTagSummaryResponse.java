package team.mut4.trip.domain.foodreviewtag.dto.response;

import lombok.Builder;

@Builder
public record FoodReviewTagSummaryResponse(

        Long id,

        String tagName,

        Long count

) {
}
