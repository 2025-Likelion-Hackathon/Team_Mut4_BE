package team.mut4.trip.domain.accommodationreview.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccommodationReview extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    private String username;

    private String content;

    @Enumerated(EnumType.STRING)
    private AccommodationGrade accommodationGrade;

    @Builder
    private AccommodationReview(
            Accommodation accommodation,
            String username,
            String content,
            AccommodationGrade accommodationGrade
    ) {
        this.accommodation = accommodation;
        this.username = username;
        this.content = content;
        this.accommodationGrade = accommodationGrade;
    }

}
