package team.mut4.trip.domain.location.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "클라이언트의 현재 위도 경도 받아와 현재 위치 저장 응답 DTO")
@Builder
public record LocationSaveResponse(

        @Schema(description = "저장된 현재 위치 ID", example = "1")
        Long locationId,

        @Schema(description = "저장된 주소", example = "충청남도 천안시 동남구 상명대길 31")
        String address

) {
}
