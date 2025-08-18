package team.mut4.trip.domain.food.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.food.dto.response.FoodDetailResponse;
import team.mut4.trip.domain.review.domain.ReviewRepository;
import team.mut4.trip.domain.review.dto.response.ReviewInfoResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public FoodDetailResponse getFoodInfoWithReviews(Long foodId) {
        Food food = foodRepository.findByFoodId(foodId);

        List<ReviewInfoResponse> reviewResponses = reviewRepository.findAllByFood(food).stream()
                .map(ReviewInfoResponse::from)
                .collect(Collectors.toList());

        return FoodDetailResponse.from(food, reviewResponses);
    }

}
