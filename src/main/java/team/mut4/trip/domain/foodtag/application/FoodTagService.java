package team.mut4.trip.domain.foodtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.foodtag.domain.FoodTag;
import team.mut4.trip.domain.foodtag.domain.FoodTagRepository;
import team.mut4.trip.domain.foodtag.dto.response.FoodTagInfoResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodTagService {

    private final FoodTagRepository foodTagRepository;

    @Transactional(readOnly = true)
    public List<FoodTagInfoResponse> getAllFoodTags() {
        List<FoodTag> foodTags = foodTagRepository.findAll();

        return foodTags.stream()
                .map(FoodTagInfoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FoodTag> getFoodTagsByIds(List<Long> foodTagIds) {
        return foodTagRepository.findByIdIn(foodTagIds);
    }

}
