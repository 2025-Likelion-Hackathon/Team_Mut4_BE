package team.mut4.trip.domain.foodreviewtag.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTagRepository;
import team.mut4.trip.domain.review.domain.Review;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FoodReviewTagRepositoryImpl implements FoodReviewTagRepository {

    private final FoodReviewTagJpaRepository foodReviewTagJpaRepository;

    @Override
    public void save(FoodReviewTag foodReviewTag) {
        foodReviewTagJpaRepository.save(foodReviewTag);
    }

    @Override
    public List<Object[]> findTagUsageCountByFood(Food food) {
        return foodReviewTagJpaRepository.findTagUsageCountByFood(food);
    }

}
