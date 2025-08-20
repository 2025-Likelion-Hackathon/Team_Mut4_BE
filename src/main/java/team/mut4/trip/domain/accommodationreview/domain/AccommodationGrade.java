package team.mut4.trip.domain.accommodationreview.domain;

import lombok.Getter;

@Getter
public enum AccommodationGrade {
    A(5),
    B(4),
    C(3),
    D(2),
    E(1);

    private final int score;

    AccommodationGrade(int score) {
        this.score = score;
    }

    public static AccommodationGrade fromScore(double avgScore) {
        for (AccommodationGrade accommodationGrade : AccommodationGrade.values()) {
            if (avgScore >= accommodationGrade.score - 0.5) {
                return accommodationGrade;
            }
        }
        return E;
    }

}
