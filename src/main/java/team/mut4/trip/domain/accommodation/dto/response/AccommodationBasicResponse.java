package team.mut4.trip.domain.accommodation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.accommodation.domain.Accommodation;

@Builder
public record AccommodationBasicResponse(

        @Schema(description = "숙소 ID", example = "1")
        Long id,

        @Schema(description = "숙소 이름", example = "라마다 호텔 천안")
        String name,

        @Schema(description = "숙소 주소 (지번)", example = "충남 천안시 서북구 쌍용동 123-45")
        String address,

        @Schema(description = "숙소 카테고리", example = "숙박 > 호텔")
        String categoryName,

        @Schema(description = "숙소 도로명 주소", example = "충남 천안시 서북구 불당대로 200")
        String roadAddress,

        @Schema(description = "숙소 전화번호", example = "041-123-4567")
        String phone,

        @Schema(description = "카카오 지도 URL", example = "http://place.map.kakao.com/12345678")
        String placeUrl,

        @Schema(description = "위도", example = "36.8154066")
        double latitude,

        @Schema(description = "경도", example = "127.1559018")
        double longitude,

        String averageGrade,

        int accommodationAveragePrice,

        int priceDifference

) {
    public static AccommodationBasicResponse from(Accommodation accommodation, String averageGrade) {
        return AccommodationBasicResponse.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .address(accommodation.getAddress())
                .categoryName(accommodation.getCategoryName())
                .roadAddress(accommodation.getRoadAddress())
                .phone(accommodation.getPhone())
                .placeUrl(accommodation.getPlaceUrl())
                .latitude(accommodation.getLatitude())
                .longitude(accommodation.getLongitude())
                .averageGrade(averageGrade)
                .accommodationAveragePrice(accommodation.getAccommodationAveragePrice())
                .priceDifference(accommodation.getPriceDifference())
                .build();
    }
}
