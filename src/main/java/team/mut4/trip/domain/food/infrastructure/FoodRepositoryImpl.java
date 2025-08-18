package team.mut4.trip.domain.food.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.food.domain.Food;
import team.mut4.trip.domain.food.domain.FoodRepository;
import team.mut4.trip.domain.food.presentation.exception.FoodNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FoodRepositoryImpl implements FoodRepository {

    private final FoodJpaRepository foodJpaRepository;

    @Override
    public Food save(Food food) {
        return foodJpaRepository.save(food);
    }

    @Override
    public Optional<Food> findByNameAndAddress(String name, String address) {
        return foodJpaRepository.findByNameAndAddress(name, address);
    }

    @Override
    public Food findByFoodId(Long foodId) {
        return foodJpaRepository.findById(foodId)
                .orElseThrow(FoodNotFoundException::new);
    }

}
