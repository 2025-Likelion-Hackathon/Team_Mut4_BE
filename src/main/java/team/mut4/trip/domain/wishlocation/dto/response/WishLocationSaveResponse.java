package team.mut4.trip.domain.wishlocation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record WishLocationSaveResponse(

        @Schema(description = "저장된 가고 싶은 위치 ID", example = "1")
        Long wishLocationId,

        @Schema(description = "저장된 주소", example = "충청남도 천안시 동남구 상명대길 31")
        String wishAddress

) {
}
