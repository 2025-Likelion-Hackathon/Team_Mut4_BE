package team.mut4.trip.domain.foodreview.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.foodreviewtag.application.FoodReviewTagService;
import team.mut4.trip.domain.foodtag.application.FoodTagService;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.foodreview.domain.FoodGrade;
import team.mut4.trip.domain.foodreview.domain.FoodReview;
import team.mut4.trip.domain.foodreview.domain.FoodReviewRepository;
import team.mut4.trip.domain.foodreview.dto.request.FoodReviewSaveRequest;
import team.mut4.trip.domain.foodreview.dto.response.FoodGradeSummaryResponse;
import team.mut4.trip.domain.foodreview.dto.response.FoodReviewSaveResponse;
import team.mut4.trip.global.util.RandomNicknameGenerator;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodReviewService {

    private final FoodReviewRepository foodReviewRepository;
    private final FoodRepository foodRepository;
    private final RandomNicknameGenerator nicknameGenerator;
    private final FoodTagService foodTagService;
    private final FoodReviewTagService foodReviewTagService;

    @Transactional
    public FoodReviewSaveResponse saveReview(Long foodId, FoodReviewSaveRequest request) {
        Food food = foodRepository.findByFoodId(foodId);

        String randomUsername = nicknameGenerator.foodGenerate();

        FoodReview foodReview = FoodReview.builder()
                .food(food)
                .username(randomUsername)
                .content(request.content())
                .foodGrade(FoodGrade.valueOf(request.grade()))
                .build();
        foodReviewRepository.save(foodReview);

        List<FoodTag> foodTags = foodTagService.getFoodTagsByIds(request.foodTagIds());
        foodReviewTagService.saveTagsForReview(foodReview, foodTags);

        return FoodReviewSaveResponse.builder()
                .reviewId(foodReview.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public FoodGradeSummaryResponse getFoodGradeSummary(Food food) {
        List<FoodReview> foodReviews = foodReviewRepository.findAllByFood(food);
        double avgScore = foodReviews.stream()
                .mapToInt(r -> r.getFoodGrade().getScore())
                .average()
                .orElse(0.0);
        FoodGrade avgFoodGrade = FoodGrade.fromScore(avgScore);
        return FoodGradeSummaryResponse.builder()
                .averageScore(avgScore)
                .averageFoodGrade(avgFoodGrade)
                .build();
    }

}
