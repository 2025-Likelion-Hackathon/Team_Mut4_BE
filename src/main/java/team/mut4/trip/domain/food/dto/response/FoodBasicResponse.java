package team.mut4.trip.domain.food.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.food.domain.Food;

@Builder
@Schema(description = "음식점 기본 정보 응답 DTO")
public record FoodBasicResponse(

        @Schema(description = "음식점 ID", example = "21")
        Long id,

        @Schema(description = "음식점 이름", example = "마리스")
        String name,

        @Schema(description = "주소", example = "충남 천안시 동남구 안서동 492-2")
        String address,

        @Schema(description = "카테고리", example = "음식점 > 카페 > 커피전문점")
        String categoryName,

        @Schema(description = "도로명 주소", example = "충남 천안시 동남구 천호지길 11")
        String roadAddress,

        @Schema(description = "전화번호", example = "070-4007-5220")
        String phone,

        @Schema(description = "카카오 지도 URL", example = "http://place.map.kakao.com/23700772")
        String placeUrl,

        @Schema(description = "위도", example = "36.8344066084594")
        double latitude,

        @Schema(description = "경도", example = "127.172901854085")
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
