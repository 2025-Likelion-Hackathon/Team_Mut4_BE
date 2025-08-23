package team.mut4.trip.domain.location.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.location.dto.request.LocationSaveRequest;
import team.mut4.trip.domain.location.dto.response.LocationSaveResponse;
import team.mut4.trip.domain.location.dto.response.MapInfoResponse;

import java.util.List;

@Tag(name = "Location", description = "현재 위치 관련 API")
@RequestMapping("/locations")
public interface LocationDocsController {

    @Operation(summary = "현재 위치 저장", description = "현재 위치 정보를 저장합니다.")
    @PostMapping
    ResponseEntity<LocationSaveResponse> saveLocation(@RequestBody LocationSaveRequest request);

    @Operation(summary = "현재 위치 기반 주변 음식점 조회 후 저장 (5개만 추출)", description = "현재 위치 주변 음식점을 조회 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-food")
    ResponseEntity<List<FoodBasicResponse>> getNearbyFoodPlacesAndSaveFood(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 음식점 조회 후 저장 (모두 추출)", description = "현재 위치 주변 음식점을 조회 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-food-all")
    ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodPlacesAndSaveFood(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 음식점 조회 후 저장 (등급순 정렬)", description = "현재 위치 주변 음식점을 등급순으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-food-all/grade")
    ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodPlacesSortedByGrade(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 숙소 조회 후 저장 (5개만 추출)", description = "현재 위치 주변 숙소를 조회 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-accommodation")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAccommodationsAndSave(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 숙소 조회 후 저장 (모두 추출)", description = "현재 위치 주변 숙소를 조회 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-accommodation-all")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodationsAndSave(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 주변 숙소 조회 후 저장 (등급순 정렬)", description = "현재 위치 주변 숙소를 등급순으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/nearby-accommodation-all/grade")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodationsSortedByGrade(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 카테고리 검색 후 저장", description = "키워드로 음식점/숙소를 검색 후 DB에 저장합니다.")
    @GetMapping("/{locationId}/search")
    ResponseEntity<List<MapInfoResponse>> searchAndSaveFood(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "맛집") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "현재 위치 기반 카테고리 검색 후 등급순 정렬", description = "키워드로 음식점/숙소를 검색 후 등급순으로 정렬합니다.")
    @GetMapping("/{locationId}/search/grade")
    ResponseEntity<List<MapInfoResponse>> searchAndSaveByCategorySortedByGrade(
            @Parameter(description = "조회할 현재 위치 ID") @PathVariable Long locationId,
            @Parameter(description = "검색 키워드") @RequestParam String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam int radius
    );

}
