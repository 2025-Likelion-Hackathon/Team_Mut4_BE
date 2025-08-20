package team.mut4.trip.domain.accommodationreview.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodation.domain.AccommodationRepository;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReviewRepository;
import team.mut4.trip.domain.accommodationreview.dto.request.AccommodationReviewSaveRequest;
import team.mut4.trip.domain.accommodationreview.dto.response.AccommodationReviewSaveResponse;
import team.mut4.trip.global.util.RandomAccommodationNicknameGenerator;

@RequiredArgsConstructor
@Service
public class AccommodationReviewService {

    private final AccommodationReviewRepository accommodationReviewRepository;
    private final AccommodationRepository accommodationRepository;
    private final RandomAccommodationNicknameGenerator randomAccommodationNicknameGenerator;

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
                .build();
        accommodationReviewRepository.save(accommodationReview);

        return AccommodationReviewSaveResponse.builder()
                .accommodationReviewId(accommodationReview.getId())
                .build();
    }

}
