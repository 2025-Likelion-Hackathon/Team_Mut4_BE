package team.mut4.trip.domain.foodreview.domain;

import team.mut4.trip.domain.food.domain.Food;

import java.util.List;

public interface FoodReviewRepository {

    void save(FoodReview foodReview);

    List<FoodReview> findAllByFood(Food food);

}
