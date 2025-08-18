package team.mut4.trip.domain.review.domain;

import jakarta.persistence.*;
import lombok.*;
import team.mut4.trip.domain.food.domain.Food;

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
