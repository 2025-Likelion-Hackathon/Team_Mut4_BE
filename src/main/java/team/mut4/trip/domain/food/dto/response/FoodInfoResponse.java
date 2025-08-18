package team.mut4.trip.domain.food.dto.response;

import lombok.Builder;
import team.mut4.trip.domain.food.domain.Food;

@Builder
public record FoodInfoResponse(
        Long id,
        String name,
        String address,
        String roadAddress,
        String phone,
        String placeUrl,
        double latitude,
        double longitude
) {
    public static FoodInfoResponse from(Food food) {
        return FoodInfoResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .address(food.getAddress())
                .roadAddress(food.getRoadAddress())
                .phone(food.getPhone())
                .placeUrl(food.getPlaceUrl())
                .latitude(food.getLatitude())
                .longitude(food.getLongitude())
                .build();
    }
}
