package team.mut4.trip.domain.wishlocationfoodbookmark.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocationfoodbookmark.domain.WishLocationFoodBookMark;
import team.mut4.trip.domain.wishlocationfoodbookmark.domain.WishLocationFoodBookMarkRepository;

@RequiredArgsConstructor
@Repository
public class WishLocationFoodBookMarkRepositoryImpl implements WishLocationFoodBookMarkRepository {

    private final WishLocationFoodBookMarkJpaRepository wishLocationFoodBookMarkJpaRepository;

    @Override
    public void save(WishLocationFoodBookMark wishLocationFoodBookMark) {
        wishLocationFoodBookMarkJpaRepository.save(wishLocationFoodBookMark);
    }

    @Override
    public WishLocationFoodBookMark findByWishLocationAndFood(WishLocation wishLocation, Food food) {
        return wishLocationFoodBookMarkJpaRepository.findByWishLocationAndFood(wishLocation, food);
    }

}
