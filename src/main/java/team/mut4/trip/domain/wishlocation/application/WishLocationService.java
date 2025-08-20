package team.mut4.trip.domain.wishlocation.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;
import team.mut4.trip.global.config.KakaoMapClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WishLocationService {

    private final WishLocationRepository wishLocationRepository;
    private final KakaoMapClient kakaoMapClient;

    @Transactional
    public WishLocationSaveResponse saveWishLocation(WishLocationSaveRequest request) {
        String address = kakaoMapClient.getAddress(request.longitude(), request.latitude());

        WishLocation wishLocation = WishLocation.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .wishAddress(address)
                .build();
        wishLocationRepository.save(wishLocation);

        return WishLocationSaveResponse.builder()
                .wishLocationId(wishLocation.getId())
                .wishAddress(address)
                .build();
    }

    public SearchResponse findNearbyFoodPlaces(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);

        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                wishLocation.getLongitude(),
                wishLocation.getLatitude(),
                radius
        );

        return SearchResponse.builder()
                .mapInfoResponseList(places)
                .build();
    }

}
