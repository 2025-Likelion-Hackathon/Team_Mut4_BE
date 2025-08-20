package team.mut4.trip.domain.accommodationreviewtag.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;
import team.mut4.trip.global.domain.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccommodationReviewTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccommodationReview accommodationReview;

    @ManyToOne(fetch = FetchType.LAZY)
    private AccommodationTag accommodationTag;

    @Builder
    private AccommodationReviewTag(
            AccommodationReview accommodationReview,
            AccommodationTag accommodationTag
    ) {
        this.accommodationReview = accommodationReview;
        this.accommodationTag = accommodationTag;
    }

}
