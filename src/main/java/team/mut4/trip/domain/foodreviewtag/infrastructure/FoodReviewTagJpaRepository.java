package team.mut4.trip.domain.foodreviewtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;

public interface FoodReviewTagJpaRepository extends JpaRepository<FoodReviewTag, Long> {
}
