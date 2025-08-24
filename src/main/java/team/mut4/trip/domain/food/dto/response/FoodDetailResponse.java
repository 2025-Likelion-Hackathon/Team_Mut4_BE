package team.mut4.trip.domain.food.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.dto.response.FoodReviewTagSummaryResponse;
import team.mut4.trip.domain.foodreview.dto.response.FoodReviewInfoResponse;

import java.util.List;

@Schema(description = "음식점 상세 정보 응답 DTO")
@Builder
public record FoodDetailResponse(

        @Schema(description = "음식점 ID", example = "1")
        Long id,

        @Schema(description = "음식점 이름", example = "산내들")
        String name,

        @Schema(description = "지번 주소", example = "충남 천안시 서북구 성정동 618-3")
        String address,

        @Schema(description = "카테고리", example = "음식점 > 한식 > 육류,고기 > 갈비")
        String categoryName,

        @Schema(description = "도로명 주소", example = "충남 천안시 서북구 서부1길 77")
        String roadAddress,

        @Schema(description = "전화번호", example = "041-578-7007")
        String phone,

        @Schema(description = "카카오맵 URL", example = "http://place.map.kakao.com/9811591")
        String placeUrl,

        @Schema(description = "위도", example = "36.8253692391523")
        double latitude,

        @Schema(description = "경도", example = "127.13786046762")
        double longitude,

        @Schema(description = "평균 평점 등급", example = "A")
        String averageGrade,

        @Schema(description = "상위 태그 리스트", example = """
                [
                  {"id": 1, "tagName": "맛있어요", "count": 1},
                  {"id": 2, "tagName": "건강해요", "count": 1},
                  {"id": 4, "tagName": "깔끔해요", "count": 1}
                ]
                """)
        List<FoodReviewTagSummaryResponse> topTags,

        @Schema(description = "리뷰 리스트", example = """
                [
                  {"id": 1, "username": "달콤한 초콜릿", "content": "string"},
                  {"id": 2, "username": "짭짤한 밥", "content": "string"}
                ]
                """)
        List<FoodReviewInfoResponse> reviews,

        @Schema(description = "메뉴 리스트", example = """
                [
                  {"menuName": "메뉴 이름 A", "menuPrice": 10000},
                  {"menuName": "메뉴 이름 A", "menuPrice": 10000},
                  {"menuName": "메뉴 이름 A", "menuPrice": 10000},
                  {"menuName": "메뉴 이름 A", "menuPrice": 10000},
                  {"menuName": "메뉴 이름 A", "menuPrice": 10000},
                ]
                """)
        List<FoodMenuInfoResponse> menus,

        @Schema(description = "해당 식당 가격", example = "10000")
        int restaurantPrice,

        @Schema(description = "지역 식당 평균 가격", example = "15000")
        int regionRestaurantAveragePrice




) {
    public static FoodDetailResponse from(Food food, String averageGrade,
                                          List<FoodReviewTagSummaryResponse> topTags,
                                          List<FoodReviewInfoResponse> reviews,
                                          List<FoodMenuInfoResponse> menus,
                                          int restaurantPrice,
                                          int regionRestaurantAveragePrice) {
        return FoodDetailResponse.builder()
                .id(food.getId())
                .name(food.getName())
                .address(food.getAddress())
                .categoryName(food.getCategoryName())
                .roadAddress(food.getRoadAddress())
                .phone(food.getPhone())
                .placeUrl(food.getPlaceUrl())
                .latitude(food.getLatitude())
                .longitude(food.getLongitude())
                .averageGrade(averageGrade)
                .topTags(topTags)
                .reviews(reviews)
                .menus(menus)
                .restaurantPrice(restaurantPrice)
                .regionRestaurantAveragePrice(regionRestaurantAveragePrice)
                .build();
    }
}
