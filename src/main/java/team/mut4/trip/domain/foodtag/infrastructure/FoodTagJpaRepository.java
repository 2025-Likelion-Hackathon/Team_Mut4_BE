package team.mut4.trip.domain.foodtag.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.foodtag.domain.FoodTag;

import java.util.List;

public interface FoodTagJpaRepository extends JpaRepository<FoodTag, Long> {

    List<FoodTag> findByIdIn(List<Long> foodTagIds);

}
