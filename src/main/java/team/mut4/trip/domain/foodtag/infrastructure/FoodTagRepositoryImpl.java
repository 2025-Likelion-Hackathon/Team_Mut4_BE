package team.mut4.trip.domain.foodtag.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.foodtag.domain.FoodTagRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FoodTagRepositoryImpl implements FoodTagRepository {

    private final FoodTagJpaRepository foodTagJpaRepository;

    @Override
    public List<FoodTag> findAll() {
        return foodTagJpaRepository.findAll();
    }

    @Override
    public List<FoodTag> findByIdIn(List<Long> foodTagIds) {
        return foodTagJpaRepository.findByIdIn(foodTagIds);
    }

}
