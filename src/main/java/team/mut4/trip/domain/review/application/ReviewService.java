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
import team.mut4.trip.global.util.RandomNicknameGenerator;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;
    private final RandomNicknameGenerator nicknameGenerator;

    @Transactional
    public ReviewSaveResponse saveReview(Long foodId, ReviewSaveRequest request) {
        Food food = foodRepository.findByFoodId(foodId);

        String randomUsername = nicknameGenerator.foodGenerate();

        Review review = Review.builder()
                .food(food)
                .username(randomUsername)
                .content(request.content())
                .build();
        reviewRepository.save(review);

        return ReviewSaveResponse.builder()
                .reviewId(review.getId())
                .build();
    }

}
