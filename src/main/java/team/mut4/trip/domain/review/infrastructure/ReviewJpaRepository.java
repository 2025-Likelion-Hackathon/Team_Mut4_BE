package team.mut4.trip.domain.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.review.domain.Review;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
