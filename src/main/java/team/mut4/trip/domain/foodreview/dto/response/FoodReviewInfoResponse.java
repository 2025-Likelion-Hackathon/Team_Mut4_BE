package team.mut4.trip.domain.foodreview.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.foodreview.domain.FoodReview;

@Builder
public record FoodReviewInfoResponse(

        Long id,

        String username,

        String content

) {
    public static FoodReviewInfoResponse from(FoodReview foodReview) {
        return new FoodReviewInfoResponse(
                foodReview.getId(),
                foodReview.getUsername(),
                foodReview.getContent()
        );
    }
}

