package team.mut4.trip.domain.location.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.domain.LocationRepository;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;
import team.mut4.trip.global.config.KakaoMapClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final KakaoMapClient kakaoMapClient;

    @Transactional
    public LocationSaveResponse saveLocation(LocationSaveRequest request) {
        String address = kakaoMapClient.getAddress(request.longitude(), request.latitude());

        Location location = Location.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .address(address)
                .build();

        locationRepository.save(location);

        return LocationSaveResponse.builder()
                .locationId(location.getId())
                .address(address)
                .build();
    }

    public SearchResponse findNearbyFoodPlaces(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);

        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                location.getLongitude(),
                location.getLatitude(),
                radius
        );

        return SearchResponse.builder()
                .mapInfoResponseList(places)
                .build();
    }

}
