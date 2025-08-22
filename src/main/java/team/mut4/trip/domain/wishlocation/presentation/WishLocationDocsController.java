package team.mut4.trip.domain.wishlocation.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationBasicResponse;
import team.mut4.trip.domain.food.dto.response.FoodBasicResponse;
import team.mut4.trip.domain.wishlocation.dto.request.WishLocationSaveRequest;
import team.mut4.trip.domain.wishlocation.dto.response.WishLocationSaveResponse;

import java.util.List;

@Tag(name = "WishLocation", description = "가고 싶은 장소 관련 API")
@RequestMapping("/wish-locations")
public interface WishLocationDocsController {

    @Operation(summary = "가고 싶은 장소 저장", description = "클라이언트가 선택한 가고 싶은 장소를 저장합니다. (클라이언트의 위도 경도 받는다.)")
    @PostMapping
    ResponseEntity<WishLocationSaveResponse> saveWishLocation(
            @RequestBody WishLocationSaveRequest request
    );

    @Operation(summary = "가고 싶은 장소 기반 주변 음식점 조회 후 저장 (5개만 추출)", description = "선택한 가고 싶은 장소 주변의 음식점을 조회 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-food")
    ResponseEntity<List<FoodBasicResponse>> getNearbyFoodPlaces(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 주변 음식점 조회 후 저장 (모두 추출)", description = "선택한 가고 싶은 장소 주변의 모든 음식점을 조회 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-food-all")
    ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodPlacesAndSaveFood(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 음식 검색 후 저장", description = "선택한 가고 싶은 장소에서 키워드 기반 음식점 검색 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/search/food/save")
    ResponseEntity<List<FoodBasicResponse>> searchAndSaveFood(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "맛집") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 음식점 모두 조회 후 평점 기준 정렬", description = "선택한 가고 싶은 장소 주변의 모든 음식점을 평점 기준으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-food-all/grade")
    ResponseEntity<List<FoodBasicResponse>> getNearbyAllFoodsSortedByGrade(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 음식 검색 후 평점 기준 정렬", description = "선택한 가고 싶은 장소에서 키워드 기반 음식점 검색 후 평점 기준으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/search/food/save/grade")
    ResponseEntity<List<FoodBasicResponse>> searchAndSaveFoodSortedByGrade(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "맛집") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 주변 숙박 조회 후 저장 (5개만 추출)", description = "선택한 가고 싶은 장소 주변의 숙박 시설을 조회 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-accommodation")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAccommodations(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 주변 숙박 조회 후 저장 (모두 추출)", description = "선택한 가고 싶은 장소 주변의 모든 숙박 시설을 조회 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-accommodation-all")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodations(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 숙박 키워드 검색 후 저장", description = "선택한 가고 싶은 장소에서 키워드 기반 숙박 시설 검색 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/search/accommodation/save")
    ResponseEntity<List<AccommodationBasicResponse>> searchAndSaveAccommodations(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "호텔") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 숙박 모두 조회 후 평점 기준 정렬", description = "선택한 가고 싶은 장소 주변의 모든 숙박 시설을 평점 기준으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/nearby-accommodation-all/grade")
    ResponseEntity<List<AccommodationBasicResponse>> getNearbyAllAccommodationsSortedByGrade(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

    @Operation(summary = "가고 싶은 장소 기반 숙박 키워드 검색 후 평점 기준 정렬", description = "선택한 가고 싶은 장소에서 키워드 기반 숙박 시설 검색 후 평점 기준으로 정렬 후 DB에 저장합니다.")
    @GetMapping("/{wishLocationId}/search/accommodation/save/grade")
    ResponseEntity<List<AccommodationBasicResponse>> searchAndSaveAccommodationsSortedByGrade(
            @Parameter(description = "조회할 가고 싶은 장소 ID") @PathVariable Long wishLocationId,
            @Parameter(description = "검색 키워드") @RequestParam(defaultValue = "호텔") String keyword,
            @Parameter(description = "조회 반경(m)") @RequestParam(defaultValue = "2000") int radius
    );

}
