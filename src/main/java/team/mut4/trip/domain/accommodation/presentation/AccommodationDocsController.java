package team.mut4.trip.domain.accommodation.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationDetailResponse;

@Tag(name = "Accommodation", description = "숙소 관련 API")
@RequestMapping("/accommodations")
public interface AccommodationDocsController {

    @Operation(summary = "숙소 상세 조회", description = "선택한 숙소의 상세 정보 및 리뷰를 조회합니다.")
    @GetMapping("/{accommodationId}")
    ResponseEntity<AccommodationDetailResponse> getAccommodation(
            @Parameter(description = "조회할 숙소 ID") @PathVariable Long accommodationId
    );

}
