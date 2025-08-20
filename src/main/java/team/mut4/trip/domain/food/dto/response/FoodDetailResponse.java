package team.mut4.trip.domain.food.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.dto.response.FoodReviewTagSummaryResponse;
import team.mut4.trip.domain.review.dto.response.ReviewInfoResponse;

import java.util.List;

@Builder
public record FoodDetailResponse(
        Long id,
        String name,
        String address,
        String roadAddress,
        String phone,
        String placeUrl,
        double latitude,
        double longitude,
        String averageGrad,
        List<FoodReviewTagSummaryResponse> topTags,
        List<ReviewInfoResponse> reviews
) {
    public static FoodDetailResponse from(Food food, String averageGrad, List<FoodReviewTagSummaryResponse> topTags, List<ReviewInfoResponse> reviews) {
        return FoodDetailResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .address(food.getAddress())
                .roadAddress(food.getRoadAddress())
                .phone(food.getPhone())
                .placeUrl(food.getPlaceUrl())
                .latitude(food.getLatitude())
                .longitude(food.getLongitude())
                .averageGrad(averageGrad)
                .topTags(topTags)
                .reviews(reviews)
                .build();
    }
}
