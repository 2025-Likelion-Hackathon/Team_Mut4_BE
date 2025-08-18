package team.mut4.trip.domain.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.review.domain.Review;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByFood(Food food);

}
