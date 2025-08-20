package team.mut4.trip.domain.accommodationreview.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;

public interface AccommodationReviewJpaRepository extends JpaRepository<AccommodationReview, Long> {
}
