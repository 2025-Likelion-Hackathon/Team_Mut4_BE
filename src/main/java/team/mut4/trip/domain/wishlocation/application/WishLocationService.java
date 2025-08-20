package team.mut4.trip.domain.wishlocation.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;
import team.mut4.trip.global.config.KakaoMapClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WishLocationService {

    private final WishLocationRepository wishLocationRepository;
    private final KakaoMapClient kakaoMapClient;
    private final FoodRepository foodRepository;

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

    @Transactional
    public List<FoodBasicResponse> findNearbyFoodPlacesAndSaveFood(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveFoodsFromPlaces(wishLocation, places, 5);
    }

    @Transactional
    public List<FoodBasicResponse> searchAndSaveFood(Long wishLocationId, String keyword, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByRestaurants(
                keyword, wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveFoodsFromPlaces(wishLocation, places, null);
    }

    private List<FoodBasicResponse> saveFoodsFromPlaces(WishLocation wishLocation, List<MapInfoResponse> places, Integer limit) {
        List<FoodBasicResponse> savedList = new ArrayList<>();
        for (MapInfoResponse place : places) {
            Food food = foodRepository.findByNameAndAddress(place.placeName(), place.addressName())
                    .orElseGet(() -> foodRepository.save(
                            Food.builder()
                                    .name(place.placeName())
                                    .address(place.addressName())
                                    .categoryName(place.categoryName())
                                    .roadAddress(place.roadAddressName())
                                    .phone(place.phone())
                                    .placeUrl(place.placeUrl())
                                    .latitude(place.latitude())
                                    .longitude(place.longitude())
                                    .wishLocation(wishLocation)
                                    .build()
                    ));
            savedList.add(FoodBasicResponse.from(food));
        }

        if (limit != null && savedList.size() > limit) {
            return savedList.subList(0, limit);
        }
        return savedList;
    }

}
