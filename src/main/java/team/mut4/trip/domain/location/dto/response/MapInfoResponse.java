package team.mut4.trip.domain.location.dto.response;

import lombok.Builder;

@Builder
public record MapInfoResponse(

        String placeName,

        String addressName

) {
}
