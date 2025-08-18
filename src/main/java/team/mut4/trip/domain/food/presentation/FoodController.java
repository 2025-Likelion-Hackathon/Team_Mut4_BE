package team.mut4.trip.domain.food.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.food.application.FoodService;
import team.mut4.trip.domain.food.dto.response.FoodInfoResponse;

@RequiredArgsConstructor
@RequestMapping("/foods")
@RestController
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodInfoResponse> getFoodInfo(@PathVariable Long foodId) {
        FoodInfoResponse response = foodService.getFoodInfo(foodId);
        return ResponseEntity.ok(response);
    }

}
