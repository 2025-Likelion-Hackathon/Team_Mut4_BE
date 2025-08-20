package team.mut4.trip.domain.review.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.foodreviewtag.application.FoodReviewTagService;
import team.mut4.trip.domain.foodtag.application.FoodTagService;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.foodtag.domain.FoodTagRepository;
import team.mut4.trip.domain.review.domain.Review;
import team.mut4.trip.domain.review.domain.ReviewRepository;
import team.mut4.trip.domain.review.dto.request.ReviewSaveRequest;
import team.mut4.trip.domain.review.dto.response.ReviewInfoResponse;
import team.mut4.trip.domain.review.dto.response.ReviewSaveResponse;
import team.mut4.trip.global.util.RandomNicknameGenerator;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;
    private final RandomNicknameGenerator nicknameGenerator;
    private final FoodTagService foodTagService;
    private final FoodReviewTagService foodReviewTagService;

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

        List<FoodTag> foodTags = foodTagService.getFoodTagsByIds(request.foodTagIds());
        foodReviewTagService.saveTagsForReview(review, foodTags);

        return ReviewSaveResponse.builder()
                .reviewId(review.getId())
                .build();
    }

}
