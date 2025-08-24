package team.mut4.trip.domain.accommodation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewInfoResponse;
import team.mut4.trip.domain.accommodationreviewtag.dto.response.AccommodationReviewTagSummaryResponse;

import java.util.List;

@Schema(description = "숙소 상세 정보 응답 DTO")
@Builder
public record AccommodationDetailResponse(

        @Schema(description = "숙소 ID", example = "1")
        Long id,

        @Schema(description = "숙소 이름", example = "힐링 게스트하우스")
        String name,

        @Schema(description = "지번 주소", example = "충남 천안시 서북구 성정동 618-3")
        String address,

        @Schema(description = "카테고리", example = "숙소 > 게스트하우스")
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
                  {"id": 1, "tagName": "청결해요", "count": 5},
                  {"id": 2, "tagName": "친절해요", "count": 3},
                  {"id": 3, "tagName": "조용해요", "count": 3}
                ]
                """)
        List<AccommodationReviewTagSummaryResponse> topTags,

        @Schema(description = "리뷰 리스트", example = """
                [
                  {"id": 1, "username": "달콤한 힐링", "content": "정말 편안했어요."},
                  {"id": 2, "username": "고소한 방", "content": "위치가 좋아요."}
                ]
                """)
        List<AccommodationReviewInfoResponse> reviews,

        @Schema(description = "해당 숙소 가격", example = "150000")
        int accommodationPrice,

        @Schema(description = "지역 숙소 평균 가격", example = "170000")
        int regionAccommodationAveragePrice

) {
    public static AccommodationDetailResponse from(
            Accommodation accommodation,
            String averageGrade,
            List<AccommodationReviewTagSummaryResponse> topTags,
            List<AccommodationReviewInfoResponse> reviews,
            int accommodationPrice,
            int regionAccommodationAveragePrice
    ) {
        return AccommodationDetailResponse.builder()
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
                .topTags(topTags)
                .reviews(reviews)
                .accommodationPrice(accommodationPrice)
                .regionAccommodationAveragePrice(regionAccommodationAveragePrice)
                .build();
    }
}
