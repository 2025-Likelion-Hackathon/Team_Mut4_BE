package team.mut4.trip.domain.accommodationtag.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;

@Builder
public record AccommodationTagInfoResponse(

        @Schema(description = "숙소 태그 ID", example = "1")
        Long accommodationTagId,

        @Schema(description = "태그 이름", example = "편안해요")
        String tagName

) {
    public static AccommodationTagInfoResponse from(AccommodationTag accommodationTag) {
        return AccommodationTagInfoResponse.builder()
                .accommodationTagId(accommodationTag.getId())
                .tagName(accommodationTag.getTagName())
                .build();
    }
}
