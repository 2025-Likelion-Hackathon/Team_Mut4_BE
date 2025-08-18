package team.mut4.trip.domain.review.domain;

import team.mut4.trip.domain.food.domain.Food;

import java.util.List;

public interface ReviewRepository {

    void save(Review review);

    List<Review> findAllByFood(Food food);

}
