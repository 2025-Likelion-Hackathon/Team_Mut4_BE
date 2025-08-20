package team.mut4.trip.domain.location.dto.response;

import java.util.List;

public record KakaoPlaceSearchResponse(List<PlaceDocument> documents) {

    public record PlaceDocument(
            String place_name,
            String address_name,
            String category_name,
            String road_address_name,
            String place_url,
            String phone,
            String x,
            String y
    ) {}

}
