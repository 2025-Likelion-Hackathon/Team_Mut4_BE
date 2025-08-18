package team.mut4.trip.domain.location.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchResponse(

        List<MapInfoResponse> mapInfoResponseList

) {
}
