package team.mut4.trip.domain.location.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "주변 장소 검색 응답 DTO")
public record SearchResponse(

        @Schema(description = "주변 장소 리스트")
        List<MapInfoResponse> mapInfoResponseList

) {}
