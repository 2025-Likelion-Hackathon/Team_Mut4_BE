package team.mut4.trip.domain.accommodationreview.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;

import java.util.List;

public interface AccommodationReviewJpaRepository extends JpaRepository<AccommodationReview, Long> {

    List<AccommodationReview> findAllByAccommodationOrderByCreatedAtDesc(Accommodation accommodation);

}
