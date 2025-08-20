package team.mut4.trip.domain.foodtag.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.mut4.trip.domain.foodtag.dto.response.FoodTagInfoResponse;

import java.util.List;

@Tag(name = "FoodTag", description = "음식 태그 관련 API")
@RequestMapping("/food-tags")
public interface FoodTagDocsController {

    @Operation(summary = "모든 음식 태그 조회", description = "DB에 저장된 모든 음식 태그 목록을 조회합니다.")
    @GetMapping
    ResponseEntity<List<FoodTagInfoResponse>> getAllFoodTags();

}
