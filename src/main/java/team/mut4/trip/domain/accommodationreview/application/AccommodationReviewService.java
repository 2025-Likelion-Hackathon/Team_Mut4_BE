package team.mut4.trip.domain.accommodationreview.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationGrade;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReviewRepository;
import team.mut4.trip.domain.accommodationreview.dto.request.AccommodationReviewSaveRequest;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewSaveResponse;
import team.mut4.trip.domain.accommodationreviewtag.application.AccommodationReviewTagService;
import team.mut4.trip.domain.accommodationtag.application.AccommodationTagService;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;
import team.mut4.trip.global.util.RandomAccommodationNicknameGenerator;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationReviewService {

    private final AccommodationReviewRepository accommodationReviewRepository;
    private final AccommodationRepository accommodationRepository;
    private final RandomAccommodationNicknameGenerator randomAccommodationNicknameGenerator;
    private final AccommodationTagService accommodationTagService;
    private final AccommodationReviewTagService accommodationReviewTagService;

    @Transactional
    public AccommodationReviewSaveResponse saveAccommodationReview(
            Long accommodationId,
            AccommodationReviewSaveRequest request
    ) {
        Accommodation accommodation = accommodationRepository.findByAccommodationId(accommodationId);

        String randomUsername = randomAccommodationNicknameGenerator.accommodationGenerate();

        AccommodationReview accommodationReview = AccommodationReview.builder()
                .accommodation(accommodation)
                .username(randomUsername)
                .content(request.content())
                .accommodationGrade(AccommodationGrade.valueOf(request.grade()))
                .build();
        accommodationReviewRepository.save(accommodationReview);

        List<AccommodationTag> accommodationTags = accommodationTagService.getAccommodationTagsByIds(request.accommodationTagIds());
        accommodationReviewTagService.saveTagsForAccommodationReview(accommodationReview, accommodationTags);

        updateFoodAverage(accommodation);

        return AccommodationReviewSaveResponse.builder()
                .accommodationReviewId(accommodationReview.getId())
                .build();
    }

    @Transactional
    protected void updateFoodAverage(Accommodation accommodation) {
        List<AccommodationReview> reviews = accommodationReviewRepository.findAllByAccommodationOrderByCreatedAtDesc(accommodation);
        double avgScore = reviews.stream()
                .mapToInt(r -> r.getAccommodationGrade().getScore())
                .average()
                .orElse(0.0);
        AccommodationGrade avgGrade = AccommodationGrade.fromScore(avgScore);

        accommodation.updateAverage(avgScore, avgGrade);
    }

}
