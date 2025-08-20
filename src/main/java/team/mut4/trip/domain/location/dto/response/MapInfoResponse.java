package team.mut4.trip.domain.location.dto.response;

import lombok.Builder;

@Builder
public record MapInfoResponse(

        String placeName,

        String addressName,

        String categoryName,

        String roadAddressName,

        String phone,

        String placeUrl,

        double latitude,

        double longitude

) {
}
