package team.mut4.trip.domain.food.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import team.mut4.trip.domain.food.domain.Food;

import java.util.Optional;

public interface FoodJpaRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByNameAndAddress(String name, String address);

}
