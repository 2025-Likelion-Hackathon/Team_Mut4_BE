package team.mut4.trip.domain.foodreviewtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTagRepository;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.review.domain.Review;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodReviewTagService {

    private final FoodReviewTagRepository foodReviewTagRepository;

    @Transactional
    public void saveTagsForReview(Review review, List<FoodTag> tags) {
        tags.forEach(tag -> {
            FoodReviewTag reviewTag = FoodReviewTag.builder()
                    .review(review)
                    .foodTag(tag)
                    .build();
            foodReviewTagRepository.save(reviewTag);
        });
    }

}
