package team.mut4.trip.domain.food.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodDetailResponse;
import team.mut4.trip.domain.foodreviewtag.application.FoodReviewTagService;
import team.mut4.trip.domain.foodreviewtag.dto.response.FoodReviewTagSummaryResponse;
import team.mut4.trip.domain.foodreview.application.FoodReviewService;
import team.mut4.trip.domain.foodreview.domain.FoodReviewRepository;
import team.mut4.trip.domain.foodreview.dto.response.FoodGradeSummaryResponse;
import team.mut4.trip.domain.foodreview.dto.response.FoodReviewInfoResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodReviewRepository foodReviewRepository;
    private final FoodReviewTagService foodReviewTagService;

    @Transactional(readOnly = true)
    public FoodDetailResponse getFoodInfoWithReviews(Long foodId) {
        Food food = foodRepository.findByFoodId(foodId);

        List<FoodReviewTagSummaryResponse> topTags = foodReviewTagService.getTop3TagsByFood(food);

        List<FoodReviewInfoResponse> reviewResponses = foodReviewRepository.findAllByFood(food).stream()
                .map(FoodReviewInfoResponse::from)
                .toList();

        return FoodDetailResponse.from(food,
                food.getAverageGrade() != null ? food.getAverageGrade().name() : "N/A",
                topTags,
                reviewResponses);
    }

}
