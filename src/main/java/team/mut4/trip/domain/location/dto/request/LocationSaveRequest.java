package team.mut4.trip.domain.location.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "클라이언트의 현재 위도 경도를 받아와 현재 위치 저장 요청 DTO")
public record LocationSaveRequest(

        @Schema(description = "위도", example = "36.833218")
        double latitude,

        @Schema(description = "경도", example = "127.178162")
        double longitude

) {
}
