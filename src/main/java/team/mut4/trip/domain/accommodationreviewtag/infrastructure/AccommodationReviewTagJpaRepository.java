package team.mut4.trip.domain.accommodationreviewtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;

public interface AccommodationReviewTagJpaRepository extends JpaRepository<AccommodationReviewTag, Long> {
}
