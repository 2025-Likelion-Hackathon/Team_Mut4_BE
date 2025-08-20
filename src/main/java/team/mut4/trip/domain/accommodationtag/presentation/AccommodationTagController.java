package team.mut4.trip.domain.accommodationtag.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.mut4.trip.domain.accommodationtag.application.AccommodationTagService;
import team.mut4.trip.domain.accommodationtag.dto.response.AccommodationTagInfoResponse;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/accommodation-tags")
@RestController
public class AccommodationTagController {

    private final AccommodationTagService accommodationTagService;

    @GetMapping
    public ResponseEntity<List<AccommodationTagInfoResponse>> getAllAccommodationTags() {
        List<AccommodationTagInfoResponse> accommodationTags = accommodationTagService.getAllAccommodationTags();
        return ResponseEntity.ok(accommodationTags);
    }

}
