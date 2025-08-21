package team.mut4.trip.domain.accommodation.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodation.dto.response.AccommodationDetailResponse;
import team.mut4.trip.domain.accommodationreview.application.AccommodationReviewService;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReviewRepository;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationGradeSummaryResponse;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewInfoResponse;
import team.mut4.trip.domain.accommodationreviewtag.application.AccommodationReviewTagService;
import team.mut4.trip.domain.accommodationreviewtag.dto.response.AccommodationReviewTagSummaryResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationReviewRepository accommodationReviewRepository;
    private final AccommodationReviewTagService accommodationReviewTagService;

    @Transactional(readOnly = true)
    public AccommodationDetailResponse getAccommodationInfoWithReviews(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);

        List<AccommodationReviewTagSummaryResponse> topTags =
                accommodationReviewTagService.getTop3TagsByAccommodation(accommodation);

        List<AccommodationReviewInfoResponse> reviewResponses =
                accommodationReviewRepository.findAllByAccommodation(accommodation).stream()
                        .map(AccommodationReviewInfoResponse::from)
                        .toList();

        return AccommodationDetailResponse.from(
                accommodation,
                accommodation.getAverageGrade() != null ? accommodation.getAverageGrade().name() : "N/A",
                topTags,
                reviewResponses
        );
    }

}
