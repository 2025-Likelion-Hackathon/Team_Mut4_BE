package team.mut4.trip.domain.wishlocation.dto.response;

import lombok.Builder;

@Builder
public record WishLocationSaveResponse(

        Long wishLocationId,

        String wishAddress

) {
}
