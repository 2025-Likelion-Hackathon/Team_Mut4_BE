package team.mut4.trip.domain.wishlocationfoodbookmark.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.wishlocation.domain.WishLocation;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WishLocationFoodBookMark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private WishLocation wishLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @Builder
    private WishLocationFoodBookMark(WishLocation wishLocation, Food food) {
        this.wishLocation = wishLocation;
        this.food = food;
    }

}
