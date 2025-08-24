package team.mut4.trip.domain.food.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "음식점 메뉴 응답 DTO")
public record FoodMenuInfoResponse(
        String menuName,
        int menuPrice
) {
}
