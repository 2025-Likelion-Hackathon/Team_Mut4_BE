package team.mut4.trip.domain.foodreviewtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTagRepository;
import team.mut4.trip.domain.foodreviewtag.dto.response.FoodReviewTagSummaryResponse;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.foodtag.domain.FoodTagRepository;
import team.mut4.trip.domain.foodreview.domain.FoodReview;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodReviewTagService {

    private final FoodReviewTagRepository foodReviewTagRepository;

    @Transactional
    public void saveTagsForReview(FoodReview foodReview, List<FoodTag> tags) {
        tags.forEach(tag -> {
            FoodReviewTag reviewTag = FoodReviewTag.builder()
                    .foodReview(foodReview)
                    .foodTag(tag)
                    .build();
            foodReviewTagRepository.save(reviewTag);
        });
    }

    public List<FoodReviewTagSummaryResponse> getTop3TagsByFood(Food food) {
        List<Object[]> tagCounts = foodReviewTagRepository.findTagUsageCountByFood(food);

        return tagCounts.stream()
                .limit(3)
                .map(row -> FoodReviewTagSummaryResponse.builder()
                        .id((Long) row[0])
                        .tagName((String) row[1])
                        .count((Long) row[2])
                        .build())
                .toList();
    }

}
