package team.mut4.trip.domain.food.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.food.dto.response.FoodDetailResponse;
import team.mut4.trip.domain.foodreviewtag.application.FoodReviewTagService;
import team.mut4.trip.domain.foodreviewtag.dto.response.FoodReviewTagSummaryResponse;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.review.domain.ReviewRepository;
import team.mut4.trip.domain.review.dto.response.ReviewInfoResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final ReviewRepository reviewRepository;
    private final FoodReviewTagService foodReviewTagService;

    @Transactional(readOnly = true)
    public FoodDetailResponse getFoodInfoWithReviews(Long foodId) {
        Food food = foodRepository.findByFoodId(foodId);

        List<FoodReviewTagSummaryResponse> topTags = foodReviewTagService.getTop3TagsByFood(food);

        List<ReviewInfoResponse> reviewResponses = reviewRepository.findAllByFood(food).stream()
                .map(ReviewInfoResponse::from)
                .toList();

        return FoodDetailResponse.from(food, topTags, reviewResponses);
    }

}
