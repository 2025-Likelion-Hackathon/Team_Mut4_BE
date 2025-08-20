package team.mut4.trip.domain.food.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.food.domain.Food;

@Builder
public record FoodBasicResponse(
        Long id,
        String name,
        String address,
        String categoryName,
        String roadAddress,
        String phone,
        String placeUrl,
        double latitude,
        double longitude
) {
    public static FoodBasicResponse from(Food food) {
        return FoodBasicResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .address(food.getAddress())
                .categoryName(food.getCategoryName())
                .roadAddress(food.getRoadAddress())
                .phone(food.getPhone())
                .placeUrl(food.getPlaceUrl())
                .latitude(food.getLatitude())
                .longitude(food.getLongitude())
                .build();
    }
}
