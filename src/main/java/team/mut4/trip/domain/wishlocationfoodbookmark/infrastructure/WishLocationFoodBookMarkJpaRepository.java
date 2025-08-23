package team.mut4.trip.domain.wishlocationfoodbookmark.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.domain.wishlocationfoodbookmark.domain.WishLocationFoodBookMark;

public interface WishLocationFoodBookMarkJpaRepository extends JpaRepository<WishLocationFoodBookMark, Long> {

    WishLocationFoodBookMark findByWishLocationAndFood(WishLocation wishLocation, Food food);

}
