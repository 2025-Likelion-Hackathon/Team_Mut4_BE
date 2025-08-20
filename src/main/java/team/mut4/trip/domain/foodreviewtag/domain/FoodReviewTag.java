package team.mut4.trip.domain.foodreviewtag.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.review.domain.Review;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FoodReviewTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_tag_id")
    private FoodTag foodTag;

    @Builder
    private FoodReviewTag(Review review, FoodTag foodTag) {
        this.review = review;
        this.foodTag = foodTag;
    }

}
