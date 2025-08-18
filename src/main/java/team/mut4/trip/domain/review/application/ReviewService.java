package team.mut4.trip.domain.review.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.review.domain.Review;
import team.mut4.trip.domain.review.domain.ReviewRepository;
import team.mut4.trip.domain.review.dto.request.ReviewSaveRequest;
import team.mut4.trip.domain.review.dto.response.ReviewSaveResponse;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public ReviewSaveResponse saveReview(Long foodId, ReviewSaveRequest request) {
        Food food = foodRepository.findByFoodId(foodId);

        Review review = Review.builder()
                .food(food)
                .content(request.content())
                .build();
        reviewRepository.save(review);

        return ReviewSaveResponse.builder()
                .reviewId(review.getId())
                .build();
    }

}
