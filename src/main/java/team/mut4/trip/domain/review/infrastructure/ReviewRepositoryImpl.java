package team.mut4.trip.domain.review.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.review.domain.Review;
import team.mut4.trip.domain.review.domain.ReviewRepository;
import team.mut4.trip.domain.review.presentation.exception.ReviewNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public void save(Review review) {
        reviewJpaRepository.save(review);
    }

    @Override
    public List<Review> findAllByFood(Food food) {
        return reviewJpaRepository.findAllByFood(food);
    }

}
