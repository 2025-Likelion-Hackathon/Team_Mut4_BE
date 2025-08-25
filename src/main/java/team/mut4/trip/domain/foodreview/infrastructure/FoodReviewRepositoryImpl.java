package team.mut4.trip.domain.foodreview.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreview.domain.FoodReview;
import team.mut4.trip.domain.foodreview.domain.FoodReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FoodReviewRepositoryImpl implements FoodReviewRepository {

    private final FoodReviewJpaRepository foodReviewJpaRepository;

    @Override
    public void save(FoodReview foodReview) {
        foodReviewJpaRepository.save(foodReview);
    }

    @Override
    public List<FoodReview> findAllByFoodOrderByCreatedAtDesc(Food food) {
        return foodReviewJpaRepository.findAllByFoodOrderByCreatedAtDesc(food);
    }

}
