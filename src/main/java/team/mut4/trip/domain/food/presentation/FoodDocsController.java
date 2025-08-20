package team.mut4.trip.domain.food.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.food.dto.response.FoodDetailResponse;

@Tag(name = "Food", description = "음식점 관련 API")
@RequestMapping("/foods")
public interface FoodDocsController {

    @Operation(summary = "음식점 상세 조회", description = "선택한 음식점의 상세 정보 및 리뷰를 조회합니다.")
    @GetMapping("/{foodId}")
    ResponseEntity<FoodDetailResponse> getFoodDetail(
            @Parameter(description = "조회할 음식점 ID") @PathVariable Long foodId
    );

}
