package team.mut4.trip.domain.foodreviewtag.domain;

import team.mut4.trip.domain.food.domain.Food;

import java.util.List;

public interface FoodReviewTagRepository {

    void save(FoodReviewTag foodReviewTag);

    List<Object[]> findTagUsageCountByFood(Food food);

}
