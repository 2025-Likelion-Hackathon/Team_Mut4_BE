package team.mut4.trip.domain.location.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "음식점/숙박 정보 DTO")
public record MapInfoResponse(

        @Schema(description = "DB ID", example = "1")
        Long id,

        @Schema(description = "장소 이름", example = "동춘옥")
        String placeName,

        @Schema(description = "주소", example = "충남 천안시 동남구 안서동 503-1")
        String addressName,

        @Schema(description = "카테고리", example = "음식점")
        String categoryName,

        @Schema(description = "도로명 주소", example = "충남 천안시 동남구 천호지길 3")
        String roadAddressName,

        @Schema(description = "전화번호", example = "041-552-5008")
        String phone,

        @Schema(description = "카카오 지도 URL", example = "http://place.map.kakao.com/21028556")
        String placeUrl,

        @Schema(description = "위도", example = "36.8337863360558")
        double latitude,

        @Schema(description = "경도", example = "127.173115692272")
        double longitude,

        @Schema(description = "평균 평점", example = "N/A")
        String averageGrade,

        int averagePrice,

        int priceDifference

) {}
