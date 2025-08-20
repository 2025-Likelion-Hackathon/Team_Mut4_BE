package team.mut4.trip.domain.foodreviewtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.foodreviewtag.domain.FoodReviewTag;
import team.mut4.trip.domain.review.domain.Review;

import java.util.List;

public interface FoodReviewTagJpaRepository extends JpaRepository<FoodReviewTag, Long> {

    @Query("SELECT frt.foodTag.id, frt.foodTag.tagName, COUNT(frt) " +
            "FROM FoodReviewTag frt " +
            "WHERE frt.review.food = :food " +
            "GROUP BY frt.foodTag.id, frt.foodTag.tagName " +
            "ORDER BY COUNT(frt) DESC")
    List<Object[]> findTagUsageCountByFood(Food food);

}
