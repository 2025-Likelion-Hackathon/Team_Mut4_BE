package team.mut4.trip.domain.wishlocationfoodbookmark.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocation.domain.WishLocationRepository;
import team.mut4.trip.domain.wishlocationfoodbookmark.domain.WishLocationFoodBookMark;
import team.mut4.trip.domain.wishlocationfoodbookmark.domain.WishLocationFoodBookMarkRepository;
import team.mut4.trip.domain.wishlocationfoodbookmark.dto.response.WishLocationFoodBookMarkSaveResponse;

@RequiredArgsConstructor
@Service
public class WishLocationFoodBookMarkService {

    private final WishLocationFoodBookMarkRepository wishLocationFoodBookMarkJpaRepository;
    private final WishLocationRepository wishLocationRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public WishLocationFoodBookMarkSaveResponse saveWishLocationFoodBookMark(Long wishLocationId, Long foodId) {
        WishLocation wishLocation = wishLocationRepository.findByWishLocationId(wishLocationId);
        Food food = foodRepository.findByFoodId(foodId);

        WishLocationFoodBookMark exists = wishLocationFoodBookMarkJpaRepository.findByWishLocationAndFood(wishLocation, food);
        if (exists != null) {
            return WishLocationFoodBookMarkSaveResponse.builder()
                    .wishLocationFoodBookMarkId(exists.getId())
                    .build();
        }

        WishLocationFoodBookMark wishLocationFoodBookMark = WishLocationFoodBookMark.builder()
                .wishLocation(wishLocation)
                .food(food)
                .build();
        wishLocationFoodBookMarkJpaRepository.save(wishLocationFoodBookMark);

        return WishLocationFoodBookMarkSaveResponse.builder()
                .wishLocationFoodBookMarkId(wishLocationFoodBookMark.getId())
                .build();
    }

}
