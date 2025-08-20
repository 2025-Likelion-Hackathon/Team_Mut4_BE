package team.mut4.trip.domain.foodtag.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.foodtag.application.FoodTagService;
import team.mut4.trip.domain.foodtag.dto.response.FoodTagInfoResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/food-tags")
public class FoodTagController {

    private final FoodTagService foodTagService;

    @GetMapping
    public ResponseEntity<List<FoodTagInfoResponse>> getAllFoodTags() {
        List<FoodTagInfoResponse> tags = foodTagService.getAllFoodTags();
        return ResponseEntity.ok(tags);
    }

}
