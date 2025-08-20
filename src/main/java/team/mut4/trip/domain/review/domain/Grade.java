package team.mut4.trip.domain.review.domain;

import lombok.Getter;

@Getter
public enum Grade {
    A(5),
    B(4),
    C(3),
    D(2),
    E(1);

    private final int score;

    Grade(int score) {
        this.score = score;
    }

    public static Grade fromScore(double avgScore) {
        for (Grade grade : Grade.values()) {
            if (avgScore >= grade.score - 0.5) {
                return grade;
            }
        }
        return E;
    }

}

