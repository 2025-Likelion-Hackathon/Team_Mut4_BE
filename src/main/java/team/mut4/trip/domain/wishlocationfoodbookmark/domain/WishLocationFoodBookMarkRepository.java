package team.mut4.trip.domain.wishlocationfoodbookmark.domain;

import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;

public interface WishLocationFoodBookMarkRepository {

    void save(WishLocationFoodBookMark wishLocationFoodBookMark);

    WishLocationFoodBookMark findByWishLocationAndFood(WishLocation wishLocation, Food food);

}
