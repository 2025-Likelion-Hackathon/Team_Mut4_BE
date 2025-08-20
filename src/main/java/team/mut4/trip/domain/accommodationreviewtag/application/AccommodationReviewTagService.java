package team.mut4.trip.domain.accommodationreviewtag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.mut4.trip.domain.accommodation.domain.Accommodation;
import team.mut4.trip.domain.accommodationreview.domain.AccommodationReview;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTag;
import team.mut4.trip.domain.accommodationreviewtag.domain.AccommodationReviewTagRepository;
import team.mut4.trip.domain.accommodationreviewtag.dto.response.AccommodationReviewTagSummaryResponse;
import team.mut4.trip.domain.accommodationtag.domain.AccommodationTag;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationReviewTagService {

    private final AccommodationReviewTagRepository accommodationReviewTagRepository;

    @Transactional
    public void saveTagsForAccommodationReview(
            AccommodationReview accommodationReview,
            List<AccommodationTag> tags
    ) {
        tags.forEach(tag -> {
           AccommodationReviewTag reviewTag = AccommodationReviewTag.builder()
                   .accommodationReview(accommodationReview)
                   .accommodationTag(tag)
                   .build();
           accommodationReviewTagRepository.save(reviewTag);
        });
    }

    public List<AccommodationReviewTagSummaryResponse> getTop3TagsByAccommodation(Accommodation accommodation) {
        List<Object[]> tagCounts = accommodationReviewTagRepository.findTagUsageCountByAccommodation(accommodation);

        return tagCounts.stream()
                .limit(3)
                .map(row -> AccommodationReviewTagSummaryResponse.builder()
                        .id((Long) row[0])
                        .tagName((String) row[1])
                        .count((Long) row[2])
                        .build())
                .toList();
    }

}
