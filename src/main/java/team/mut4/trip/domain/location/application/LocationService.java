package team.mut4.trip.domain.location.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.domain.Location;
import team.mut4.trip.domain.location.domain.LocationRepository;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;
import team.mut4.trip.global.config.KakaoMapClient;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final KakaoMapClient kakaoMapClient;
    private final FoodRepository foodRepository;
    private final AccommodationRepository accommodationRepository;

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

    @Transactional
    public List<FoodBasicResponse> findNearbyFoodPlacesAndSaveFood(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                location.getLongitude(), location.getLatitude(), radius
        );

        return saveFoodsFromPlaces(location, places, 5);
    }

    @Transactional
    public List<FoodBasicResponse> findAndSaveAllNearbyFoodPlaces(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                location.getLongitude(), location.getLatitude(), radius
        );

        return saveFoodsFromPlaces(location, places, null);
    }

    @Transactional
    public List<FoodBasicResponse> searchAndSaveFood(Long locationId, String keyword, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByRestaurants(
                keyword, location.getLongitude(), location.getLatitude(), radius
        );

        return saveFoodsFromPlaces(location, places, null);
    }

    private List<FoodBasicResponse> saveFoodsFromPlaces(Location location, List<MapInfoResponse> places, Integer limit) {
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
                                    .location(location)
                                    .build()
                    ));
            savedList.add(FoodBasicResponse.from(food, food.getAverageGrade() != null ? food.getAverageGrade().name() : "N/A"));
        }

        if (limit != null && savedList.size() > limit) {
            return savedList.subList(0, limit);
        }
        return savedList;
    }

    @Transactional
    public List<AccommodationBasicResponse> findNearbyAccommodationsAndSave(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                location.getLongitude(), location.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(location, places, 5);
    }

    @Transactional
    public List<AccommodationBasicResponse> findAndSaveAllNearbyAccommodations(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                location.getLongitude(), location.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(location, places, null);
    }

    @Transactional
    public List<AccommodationBasicResponse> searchAndSaveAccommodations(Long locationId, String keyword, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchKeywordByAccommodations(
                keyword, location.getLongitude(), location.getLatitude(), radius
        );

        return saveAccommodationsFromPlaces(location, places, null);
    }

    private List<AccommodationBasicResponse> saveAccommodationsFromPlaces(Location location, List<MapInfoResponse> places, Integer limit) {
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
                                    .location(location)
                                    .build()
                    ));
            savedList.add(AccommodationBasicResponse.from(accommodation, accommodation.getAverageGrade() != null ? accommodation.getAverageGrade().name() : "N/A"));
        }

        if (limit != null && savedList.size() > limit) {
            return savedList.subList(0, limit);
        }
        return savedList;
    }

}