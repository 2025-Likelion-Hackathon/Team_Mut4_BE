package team.mut4.trip.domain.foodreview.domain;

import lombok.Getter;

@Getter
public enum FoodGrade {
    A(5),
    B(4),
    C(3),
    D(2),
    E(1);

    private final int score;

    FoodGrade(int score) {
        this.score = score;
    }

    public static FoodGrade fromScore(double avgScore) {
        for (FoodGrade foodGrade : FoodGrade.values()) {
            if (avgScore >= foodGrade.score - 0.5) {
                return foodGrade;
            }
        }
        return E;
    }

}

