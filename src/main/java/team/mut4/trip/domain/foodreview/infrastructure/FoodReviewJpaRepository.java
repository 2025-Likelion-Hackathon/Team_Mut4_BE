package team.mut4.trip.domain.foodreview.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreview.domain.FoodReview;

import java.util.List;

public interface FoodReviewJpaRepository extends JpaRepository<FoodReview, Long> {

    List<FoodReview> findAllByFoodOrderByCreatedAtDesc(Food food);

}
