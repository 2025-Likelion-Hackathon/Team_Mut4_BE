package team.mut4.trip.domain.review.domain;

import jakarta.persistence.*;
import lombok.*;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    private String username;

    private String content;

    @OneToMany(mappedBy = "review")
    private List<FoodReviewTag> foodReviewTag;

    @Builder
    private Review(
            Food food,
            String username,
            String content
    ) {
        this.food = food;
        this.username = username;
        this.content = content;
    }

}
