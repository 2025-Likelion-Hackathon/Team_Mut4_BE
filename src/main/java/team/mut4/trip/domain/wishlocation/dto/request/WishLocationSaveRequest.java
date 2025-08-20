package team.mut4.trip.domain.wishlocation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record WishLocationSaveRequest(

        @Schema(description = "위도", example = "36.833218")
        double latitude,

        @Schema(description = "경도", example = "127.178162")
        double longitude

) {
}
