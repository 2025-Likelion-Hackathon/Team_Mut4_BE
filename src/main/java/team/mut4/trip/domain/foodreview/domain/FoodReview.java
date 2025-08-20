package team.mut4.trip.domain.foodreview.domain;

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
public class FoodReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    private String username;

    private String content;

    @Enumerated(EnumType.STRING)
    private FoodGrade foodGrade;

    @OneToMany(mappedBy = "foodReview")
    private List<FoodReviewTag> foodReviewTag;

    @Builder
    private FoodReview(
            Food food,
            String username,
            String content,
            FoodGrade foodGrade
    ) {
        this.food = food;
        this.username = username;
        this.content = content;
        this.foodGrade = foodGrade;
    }

}
