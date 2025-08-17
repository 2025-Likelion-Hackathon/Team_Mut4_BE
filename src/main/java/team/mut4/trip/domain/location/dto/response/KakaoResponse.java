package team.mut4.trip.domain.location.dto.response;

import java.util.List;

public record KakaoResponse(List<Document> documents) {

    public record Document(
            RoadAddress road_address,
            Address address
    ) {}

    public record RoadAddress(String address_name) {}

    public record Address(String address_name) {}

}
