package team.mut4.trip.domain.wishlocation.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;
import team.mut4.trip.global.config.KakaoMapClient;
import team.mut4.trip.global.util.GradeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WishLocationService {

    private final WishLocationRepository wishLocationRepository;
    private final KakaoMapClient kakaoMapClient;
    private final FoodRepository foodRepository;
    private final AccommodationRepository accommodationRepository;

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
    public List<FoodBasicResponse> findAndSaveAllNearbyFoodPlaces(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveFoodsFromPlaces(wishLocation, places, null);
    }

    @Transactional
    public List<FoodBasicResponse> searchAndSaveFood(Long wishLocationId, String keyword, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByRestaurants(
                keyword, wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveFoodsFromPlaces(wishLocation, places, null);
    }

    @Transactional
    public List<FoodBasicResponse> findAndSaveAllNearbyFoodPlacesSortedByGrade(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return processAndReturnFoods(wishLocation, places, foodGradeComparator());
    }

    @Transactional
    public List<FoodBasicResponse> searchAndSaveFoodSortedByGrade(Long wishLocationId, String keyword, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByRestaurants(
                keyword, wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return processAndReturnFoods(wishLocation, places, foodGradeComparator());
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
            savedList.add(FoodBasicResponse.from(food, food.getAverageGrade() != null ? food.getAverageGrade().name() : "N/A"));
        }

        if (limit != null && savedList.size() > limit) {
            return savedList.subList(0, limit);
        }
        return savedList;
    }

    private List<FoodBasicResponse> processAndReturnFoods(
            WishLocation wishLocation,
            List<MapInfoResponse> places,
            Comparator<FoodBasicResponse> comparator
    ) {
        List<FoodBasicResponse> savedList = saveFoodsFromPlaces(wishLocation, places, null);

        if (comparator != null) {
            savedList.sort(comparator);
        }

        return savedList;
    }

    private Comparator<FoodBasicResponse> foodGradeComparator() {
        return Comparator.comparingInt(f -> GradeUtil.toRank(f.averageGrade()));
    }

    @Transactional
    public List<AccommodationBasicResponse> findNearbyAccommodationsAndSave(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(wishLocation, places, 5);
    }

    @Transactional
    public List<AccommodationBasicResponse> findAndSaveAllNearbyAccommodations(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(wishLocation, places, null);
    }

    @Transactional
    public List<AccommodationBasicResponse> searchAndSaveAccommodations(Long wishLocationId, String keyword, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByAccommodations(
                keyword, wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(wishLocation, places, null);
    }

    @Transactional
    public List<AccommodationBasicResponse> findAndSaveAllNearbyAccommodationsSortedByGrade(Long wishLocationId, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return processAndReturnAccommodations(wishLocation, places, accommodationGradeComparator());
    }

    @Transactional
    public List<AccommodationBasicResponse> searchAndSaveAccommodationsSortedByGrade(Long wishLocationId, String keyword, int radius) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByAccommodations(
                keyword, wishLocation.getLongitude(), wishLocation.getLatitude(), radius
        );

        return processAndReturnAccommodations(wishLocation, places, accommodationGradeComparator());
    }

    private List<AccommodationBasicResponse> saveAccommodationsFromPlaces(WishLocation wishLocation, List<MapInfoResponse> places, Integer limit) {
        List<AccommodationBasicResponse> savedList = new ArrayList<>();
        for (MapInfoResponse place : places) {
            Accommodation accommodation = accommodationRepository.findByNameAndAddress(place.placeName(), place.addressName())
                    .orElseGet(() -> accommodationRepository.save(
                            Accommodation.builder()
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
            savedList.add(AccommodationBasicResponse.from(accommodation, accommodation.getAverageGrade() != null ? accommodation.getAverageGrade().name() : "N/A"));
        }

        if (limit != null && savedList.size() > limit) {
            return savedList.subList(0, limit);
        }
        return savedList;
    }

    private List<AccommodationBasicResponse> processAndReturnAccommodations(
            WishLocation wishLocation,
            List<MapInfoResponse> places,
            Comparator<AccommodationBasicResponse> comparator
    ) {
        List<AccommodationBasicResponse> savedList = saveAccommodationsFromPlaces(wishLocation, places, null);

        if (comparator != null) {
            savedList.sort(comparator);
        }

        return savedList;
    }

    private Comparator<AccommodationBasicResponse> accommodationGradeComparator() {
        return Comparator.comparingInt(a -> GradeUtil.toRank(a.averageGrad()));
    }

}
