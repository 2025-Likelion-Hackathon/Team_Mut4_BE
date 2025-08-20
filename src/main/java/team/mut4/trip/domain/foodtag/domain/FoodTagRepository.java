package team.mut4.trip.domain.foodtag.domain;

import java.util.List;

public interface FoodTagRepository {

    List<FoodTag> findAll();

    List<FoodTag> findByIdIn(List<Long> foodTagIds);

}
