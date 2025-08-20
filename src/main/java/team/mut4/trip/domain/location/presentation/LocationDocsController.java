package team.mut4.trip.domain.location.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.SearchResponse;

import java.util.List;

@Tag(name = "Location", description = "현재 위치 관련 API")
@RequestMapping("/locations")
public interface LocationDocsController {

    @Operation(summary = "현재 위치 저장", description = "현재 위치 정보를 저장합니다.")
    @PostMapping
    ResponseEntity<LocationSaveResponse> saveLocation(@RequestBody LocationSaveRequest request);

    @Operation(summary = "현재 위치 기반 주변 음식점 조회", description = "현재 위치 주변 음식점을 조회합니다.")
    @GetMapping("/{locationId}/nearby-food")
    ResponseEntity<SearchResponse> getNearbyFoodPlaces(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 숙박 조회", description = "현재 위치 주변 숙박 시설을 조회합니다.")
    @GetMapping("/{locationId}/nearby-accommodation")
    ResponseEntity<SearchResponse> findNearbyAccommodation(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 음식 검색 후 저장", description = "현재 위치에서 키워드 기반으로 음식점 검색 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/search/food/save")
    ResponseEntity<List<FoodBasicResponse>> searchAndSaveFood(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "맛집") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 숙박 키워드 검색", description = "현재 위치에서 키워드 기반으로 숙박 시설을 검색합니다.")
    @GetMapping("/{locationId}/search/accommodation")
    ResponseEntity<SearchResponse> searchByKeyword(
            @Parameter(description = "조회할 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "호텔") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

}
