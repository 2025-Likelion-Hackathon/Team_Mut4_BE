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
import team.mut4.trip.global.config.KakaoMapClient;
import team.mut4.trip.global.util.GradeUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LocationService {

    private static final int RESTAURANT_ABASE_PRICE = 10000;
    private static final int ACCOMMODATION_ABASE_PRICE = 150000;

    private final LocationRepository locationRepository;
    private final KakaoMapClient kakaoMapClient;
    private final FoodRepository foodRepository;
    private final AccommodationRepository accommodationRepository;

    @Transactional
    public LocationSaveResponse saveLocation(LocationSaveRequest request) {
        String address = kakaoMapClient.getAddress(request.longitude(), request.latitude());

        Optional<Location> existingLocation = locationRepository.findByAddress(address);

        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            return LocationSaveResponse.builder()
                    .locationId(location.getId())
                    .address(location.getAddress())
                    .build();
        }

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
        return processAndReturnFoods(location, places, 5, null);
    }

    @Transactional
    public List<FoodBasicResponse> findAndSaveAllNearbyFoodPlaces(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                location.getLongitude(), location.getLatitude(), radius
        );
        return processAndReturnFoods(location, places, null, null);
    }

    @Transactional
    public List<FoodBasicResponse> findAndSaveAllNearbyFoodPlacesSortedByGrade(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyRestaurants(
                location.getLongitude(), location.getLatitude(), radius
        );
        return processAndReturnFoods(location, places, null, gradeComparator());
    }

    private List<FoodBasicResponse> saveFoodsFromPlaces(Location location, List<MapInfoResponse> places, Integer limit) {
        List<FoodBasicResponse> savedList = new ArrayList<>();
        for (MapInfoResponse place : places) {
            int foodAveragePrice = getRandomRestaurantPrice();
            int priceDifference = foodAveragePrice - RESTAURANT_ABASE_PRICE;
            String normalizedName = place.placeName().trim();
            String normalizedAddress = place.addressName().trim();
            Food food = foodRepository.findByNameAndAddress(normalizedName, normalizedAddress)
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
                                    .foodAveragePrice(foodAveragePrice)
                                    .priceDifference(priceDifference)
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
            Location location,
            List<MapInfoResponse> places,
            Integer limit,
            Comparator<FoodBasicResponse> comparator
    ) {
        List<FoodBasicResponse> savedList = saveFoodsFromPlaces(location, places, limit);

        if (comparator != null) {
            savedList.sort(comparator);
        }

        return savedList;
    }

    private Comparator<FoodBasicResponse> gradeComparator() {
        return Comparator.comparingInt(f -> GradeUtil.toRank(f.averageGrade()));
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
    public List<AccommodationBasicResponse> findAndSaveAllNearbyAccommodationsSortedByGrade(Long locationId, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyAccommodations(
                location.getLongitude(), location.getLatitude(), radius
        );

        return processAndReturnAccommodations(location, places, null, accommodationGradeComparator());
    }

    private List<AccommodationBasicResponse> saveAccommodationsFromPlaces(Location location, List<MapInfoResponse> places, Integer limit) {
        List<AccommodationBasicResponse> savedList = new ArrayList<>();
        for (MapInfoResponse place : places) {
            int accommodationAveragePrice = getRandomAccommodationPrice();
            int priceDifference = accommodationAveragePrice - ACCOMMODATION_ABASE_PRICE;
            String normalizedName = place.placeName().trim();
            String normalizedAddress = place.addressName().trim();
            Accommodation accommodation = accommodationRepository.findByNameAndAddress(normalizedName, normalizedAddress)
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
                                    .accommodationAveragePrice(accommodationAveragePrice)
                                    .priceDifference(priceDifference)
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
            Location location,
            List<MapInfoResponse> places,
            Integer limit,
            Comparator<AccommodationBasicResponse> comparator
    ) {
        List<AccommodationBasicResponse> savedList = saveAccommodationsFromPlaces(location, places, limit);

        if (comparator != null) {
            savedList.sort(comparator);
        }

        return savedList;
    }

    private Comparator<AccommodationBasicResponse> accommodationGradeComparator() {
        return Comparator.comparingInt(a -> GradeUtil.toRank(a.averageGrade()));
    }

    @Transactional
    public List<MapInfoResponse> searchAndSaveByCategory(Long locationId, String keyword, int radius) {
        Location location = locationRepository.findByLocationId(locationId);
        List<MapInfoResponse> places = kakaoMapClient.searchNearbyByKeyword(
                keyword, location.getLongitude(), location.getLatitude(), radius
        );

        List<MapInfoResponse> result = new ArrayList<>();

        for (MapInfoResponse place : places) {
            int foodAveragePrice = getRandomRestaurantPrice();
            int priceDifference = foodAveragePrice - RESTAURANT_ABASE_PRICE;
            if (place.categoryName().startsWith("음식점")) {
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
                                        .foodAveragePrice(foodAveragePrice)
                                        .priceDifference(priceDifference)
                                        .build()
                        ));

                result.add(MapInfoResponse.builder()
                        .id(food.getId())
                        .placeName(food.getName())
                        .addressName(food.getAddress())
                        .categoryName(food.getCategoryName())
                        .roadAddressName(food.getRoadAddress())
                        .phone(food.getPhone())
                        .placeUrl(food.getPlaceUrl())
                        .latitude(food.getLatitude())
                        .longitude(food.getLongitude())
                        .averageGrade(food.getAverageGrade() != null ? food.getAverageGrade().name() : "N/A")
                        .averagePrice(food.getFoodAveragePrice())
                        .priceDifference(food.getPriceDifference())
                        .build());
            } else if (place.categoryName().startsWith("여행")) {
                int accommodationAveragePrice = getRandomAccommodationPrice();
                int accommodationPriceDifference = accommodationAveragePrice - ACCOMMODATION_ABASE_PRICE;
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
                                        .accommodationAveragePrice(accommodationAveragePrice)
                                        .priceDifference(accommodationPriceDifference)
                                        .build()
                        ));

                result.add(MapInfoResponse.builder()
                        .id(accommodation.getId())
                        .placeName(accommodation.getName())
                        .addressName(accommodation.getAddress())
                        .categoryName(accommodation.getCategoryName())
                        .roadAddressName(accommodation.getRoadAddress())
                        .phone(accommodation.getPhone())
                        .placeUrl(accommodation.getPlaceUrl())
                        .latitude(accommodation.getLatitude())
                        .longitude(accommodation.getLongitude())
                        .averageGrade(accommodation.getAverageGrade() != null ? accommodation.getAverageGrade().name() : "N/A")
                        .averagePrice(accommodation.getAccommodationAveragePrice())
                        .priceDifference(accommodation.getPriceDifference())
                        .build());
            }
        }

        return result;
    }

    @Transactional
    public List<MapInfoResponse> searchAndSaveByCategorySortedByGrade(Long locationId, String keyword, int radius) {
        List<MapInfoResponse> result = searchAndSaveByCategory(locationId, keyword, radius);

        result.sort((a, b) -> {
            int rankA = a.averageGrade().equals("N/A") ? -1 : GradeUtil.toRank(a.averageGrade());
            int rankB = b.averageGrade().equals("N/A") ? -1 : GradeUtil.toRank(b.averageGrade());
            return Integer.compare(rankB, rankA);
        });

        return result;
    }

    private int getRandomRestaurantPrice() {
        double min = RESTAURANT_ABASE_PRICE * 0.8;
        double max = RESTAURANT_ABASE_PRICE * 1.2;
        int price = (int) (min + Math.random() * (max - min));
        return Math.round(price / 100f) * 100;
    }

    private int getRandomAccommodationPrice() {
        double min = ACCOMMODATION_ABASE_PRICE * 0.8;
        double max = ACCOMMODATION_ABASE_PRICE * 1.2;
        int price = (int) (min + Math.random() * (max - min));
        return Math.round(price / 1000f) * 1000;
    }

}