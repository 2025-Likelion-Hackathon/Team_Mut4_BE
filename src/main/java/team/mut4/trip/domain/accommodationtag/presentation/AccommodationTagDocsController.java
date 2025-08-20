package team.mut4.trip.domain.accommodationtag.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.mut4.trip.domain.accommodationtag.dto.response.AccommodationTagInfoResponse;

import java.util.List;

@Tag(name = "AccommodationTag", description = "숙박 태그 관련 API")
@RequestMapping("/accommodation-tags")
public interface AccommodationTagDocsController {

    @Operation(summary = "모든 숙박 태그 조회", description = "DB에 저장된 모든 숙박 태그 목록을 조회합니다.")
    @GetMapping
    ResponseEntity<List<AccommodationTagInfoResponse>> getAllAccommodationTags();

}
